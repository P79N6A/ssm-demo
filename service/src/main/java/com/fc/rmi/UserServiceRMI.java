package com.fc.rmi;

import java.rmi.Remote;
import java.rmi.RemoteException;

import com.fc.bean.User;

/**
 * @author fangcong on 2017/8/8.
 */
public interface UserServiceRmi extends Remote {

    /**
     * 根据用户名查询用户
     * @param name
     * @return
     */
    User getUserByName(String name) throws RemoteException;
}
