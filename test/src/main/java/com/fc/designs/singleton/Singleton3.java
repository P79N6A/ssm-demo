package com.fc.designs.singleton;

/**
 * Created by fangcong on 2017/2/20.
 * 单例模式(饿汉式)
 */
public class Singleton3 {

    /**
     * 初始化时即实例化一次
     */
    private static final Singleton3 INSTANCE = new Singleton3();

    private Singleton3(){}

    public static Singleton3 getInstance(){
        return INSTANCE;
    }
}
