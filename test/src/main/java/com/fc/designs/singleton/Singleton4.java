package com.fc.designs.singleton;

/**
 * @author fangcong on 2017/2/20.
 * 静态内部类的方式获取单例对象
 */
public class Singleton4 {

    public static Integer STATUS = 4;

    /**
     * 静态内部类用来定义单例对象
     */
    private static class SingletonHolder{
        private final static Singleton4 INSTANCE = new Singleton4();
    }

    /**
     * 私有的无参构造方法
     */
    private Singleton4(){
        System.out.println("instance4 init...");
    }

    /**
     * 共有方法获取单例对象
     * @return
     */
    public static Singleton4 getInstance() {
        return SingletonHolder.INSTANCE;
    }
}
