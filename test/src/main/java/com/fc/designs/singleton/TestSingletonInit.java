package com.fc.designs.singleton;

/**
 * @author fangcong on 2018/4/3.
 */
public class TestSingletonInit {

    public static void main(String[] args) {
        System.out.println(Singleton3.status);

        System.out.println(Singleton4.STATUS);
        Singleton4 singleton4 = Singleton4.getInstance();
        System.out.println(singleton4);
    }
}
