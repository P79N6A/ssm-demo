package com.fc.rmi;

import java.rmi.Remote;
import java.rmi.RemoteException;

import com.fc.bean.User;

/**
 * @author fangcong on 2017/8/8.
 */
public interface UserServiceRmi extends Remote {

    /**
     * 查询用户信息，测试rmi调用
     *
     * @param name
     * @return
     * @throws RemoteException
     */
    User getUserByName(String name) throws RemoteException;
}
