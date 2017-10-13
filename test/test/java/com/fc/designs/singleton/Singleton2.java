package com.fc.designs.singleton;

/**
 * @author fangcong on 2017/2/20.
 * 线程安全的单例模式（懒汉式）,饿汉式见Singleton3
 */
public class Singleton2 {

    /**
     * volatile关键字保证可见性
     *
     */
    private volatile static Singleton2 instance;

    private Singleton2(){}

    /**
     * 双重检查锁获取实例
     * @return
     */
    public static synchronized Singleton2 getInstance(){
        //single check
        if (instance == null){
            synchronized(Singleton2.class){
                //double check
                if (instance == null){
                    instance = new Singleton2();
                }
            }
        }
        return instance;
    }
}
