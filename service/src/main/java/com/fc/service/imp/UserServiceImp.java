package com.fc.service.imp;

import com.fc.bean.User;
import com.fc.dao.UserDao;
import com.fc.ehcache.MyCacheManager;
import com.fc.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by fangcong on 2016/11/30.
 */
@Service
@Transactional(rollbackFor = Exception.class)
public class UserServiceImp implements UserService {

    private MyCacheManager<User> cacheManager = new MyCacheManager<>();
    private static Logger logger = LoggerFactory.getLogger(UserService.class);

    @Resource
    private UserDao userDao;

    @Override
    @Transactional(propagation = Propagation.NOT_SUPPORTED)
    public List<User> queryUser(String name){
        List<User> list = userDao.queryUser(name, null);
        return list;
    }

    @Override
    @Transactional(propagation = Propagation.NOT_SUPPORTED)
    public User queryByLoginName(String loginName){
        User user = cacheManager.getValue(loginName);
        if (user != null){
            logger.debug("query from ehcache");
        }else{
            List<User> list = userDao.queryUser(null, loginName);
            if (list.size() > 0){
                user = list.get(0);
                cacheManager.addOrUpdateCache(loginName,user);
            }
            logger.debug("query from DB");
        }
        return user;
    }

    /**
     * 验证事务注解是否生效，可以加上readOnly = true <br>
     * 执行新增或修改操作时抛出500异常证明注解生效，否则检查配置tx:annotation-driven<br>
     * @param user
     * @return
     */
    @Override
    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
    public User addUser(User user){
        userDao.addUser(user);
        return user;
    }

    @Override
    public boolean existUser(User user) {
        return userDao.checkLoginIsRight(user) > 0;
    }
}
