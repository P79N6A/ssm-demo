package com.fc.demo.reflect;

/**
 * Created by fangcong on 2017/4/18.
 */
public class HelloServiceImp implements HelloService {

    @Override
    public void sayHello(String name) {
        System.out.println("say : " + name);
    }
}
