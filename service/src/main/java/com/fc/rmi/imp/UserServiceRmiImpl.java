package com.fc.rmi.imp;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.List;

import javax.annotation.Resource;

import com.fc.bean.User;
import com.fc.dao.UserDao;
import com.fc.rmi.UserServiceRmi;
import org.springframework.stereotype.Service;

/**
 * @author fangcong on 2017/8/8.
 */
@Service
public class UserServiceRmiImpl extends UnicastRemoteObject implements UserServiceRmi {

    @Resource
    private UserDao userDao;

    public UserServiceRmiImpl() throws RemoteException {
        super();
    }

    @Override
    public User getUserByName(String name) throws RemoteException{
        List<User> list = userDao.queryUser(name, null);
        if (list.size() > 0) {
            return list.get(0);
        }
        return null;
    }
}
