package com.fc.designs.singleton;

/**
 * @author fangcong on 2017/2/20.
 * 单例模式(饿汉式)
 */
public class Singleton3 {

    /**
     * 共有静态变量的访问也会促使静态实例的初始化
     */
    public static int status = 1;

    /**
     * 初始化时即实例化一次
     */
    private static final Singleton3 INSTANCE = new Singleton3();

    /**
     * 私有是为了避免随意构造实例
     */
    private Singleton3(){
        System.out.println("instance init...");
    }

    public static Singleton3 getInstance(){
        return INSTANCE;
    }
}
