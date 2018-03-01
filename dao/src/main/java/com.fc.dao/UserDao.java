package com.fc.dao;

import java.util.List;
import java.util.Set;

import com.fc.bean.FileDO;
import com.fc.bean.User;
import org.apache.ibatis.annotations.Param;

/**
 * @author fangcong on 2016/11/30.
 */
public interface UserDao {

    /**
     * 查询用户信息
     *
     * @param realName
     * @param loginName
     * @return
     */
    List<User> queryUser(@Param("realName") String realName, @Param("loginName") String loginName);

    /**
     * 添加用户
     *
     * @param user
     * @return
     */
    Integer addUser(User user);

    /**
     * 校验登陆账号
     *
     * @param user
     * @return
     */
    Integer checkLoginIsRight(User user);

    /**
     * 保存上传文件信息
     *
     * @param fileDO
     * @return
     */
    Integer insertFileInfo(FileDO fileDO);

    List<FileDO> testForEach(List<Set<String>> list);
}
