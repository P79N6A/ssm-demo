package com.demo.io;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

import com.fc.bean.User;

/**
 * 对象流读取与写入
 *
 * @author fangcong on 2018/7/18.
 */
public class ObjectInputStreamRw {

    public static void main(String[] args) throws Exception {
        ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("user.dat"));

        User user1 = new User("张三", 'M', 18);
        User user2 = new User("李四", 'F', 20);
        oos.writeObject(user1);
        oos.writeObject(user2);
        oos.close();

        ObjectInputStream ois = new ObjectInputStream(new FileInputStream("user.dat"));
        User user3 = (User)ois.readObject();
        User user4 = (User)ois.readObject();
        System.out.println(user3.toString());
        System.out.println(user4.toString());
        ois.close();
    }
}
