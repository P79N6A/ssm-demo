package com.fc.dao;

import java.util.List;

import com.fc.bean.FileDO;
import com.fc.bean.User;
import org.apache.ibatis.annotations.Param;

/**
 * Created by fangcong on 2016/11/30.
 */
public interface UserDao {

    List<User> queryUser(@Param("realName") String realName, @Param("loginName") String loginName);

    Integer addUser(User user);

    Integer checkLoginIsRight(User user);

    Integer insertFileInfo(FileDO fileDO);
}
