package com.fc.demo;

import com.fc.enums.Gender;

import java.io.*;

/**
 * Created by fangcong on 2017/2/28.
 */
public class SimpleSerial {

    public static void main(String[] args) throws Exception{
        File file = new File("person.out");
        ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(file));
        Person person = new Person("zhangsan",22, Gender.FAMALE);
        oos.writeObject(person);
        oos.close();
        ObjectInputStream ois = new ObjectInputStream(new FileInputStream(file));
        Object object = ois.readObject();
        ois.close();
        System.out.println(object);
    }
}
