package com.fc.rmi.client;

import java.rmi.Naming;

import com.fc.bean.User;
import com.fc.rmi.UserServiceRMI;

/**
 * @author fangcong on 2017/8/8.
 */
public class UserServiceClient {

    public static void main(String[] args) {
        try {
            UserServiceRMI serviceRMI = (UserServiceRMI)Naming.lookup("rmi://127.0.0.1:6600/UserServiceRMI");
            User user = serviceRMI.getUserByName("zhangsan");
            System.out.println(user.toString());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
