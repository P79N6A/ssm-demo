package com.fc.service;

import java.util.List;

import com.fc.bean.User;

/**
 * @author fangcong on 2016/11/30.
 */
public interface UserService {

    /**
     * 根据用户名查询用户信息
     *
     * @param name
     * @return
     */
    List<User> queryUser(String name);

    /**
     * 根据登陆名查询用户信息
     *
     * @param loginName
     * @return
     */
    User queryByLoginName(String loginName);

    /**
     * 添加用户
     *
     * @param user
     * @return
     */
    User addUser(User user);

    /**
     * 查询用户是否存在
     *
     * @param user
     * @return
     */
    boolean existUser(User user);

}
