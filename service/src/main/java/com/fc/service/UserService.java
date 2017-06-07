package com.fc.service;

import com.fc.bean.User;

import java.util.List;

/**
 * Created by fangcong on 2016/11/30.
 */
public interface UserService {

    List<User> queryUser(String name);

    User queryByLoginName(String loginName);

    User addUser(User user);

    boolean existUser(User user);

}
