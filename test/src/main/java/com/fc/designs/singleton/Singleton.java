package com.fc.designs.singleton;

/**
 * @author fangcong on 2017/1/23.
 * 单例模式
 */
public class Singleton{

    /**
     * ************************************************
     * <p>单例模式的特点总结：</p>
     * 1、类的实例对象只有一个<br>
     * 2、可以通过类自身的方法创建类的实例变量<br>
     * 3、系统使用的对象为同一对象<br>
     * ************************************************
     * <p>单例模式的使用方法：</p>
     * 推荐使用双重检验锁方式，或直接饿汉式方式，枚举很少使用
     * ************************************************
     * <p>单例模式的注意事项：</p>
     * 单例模式是有范围的，只局限于类加载器(ClassLoader) 中能保证唯一，当有不同类加载器时
     * 会出现不同的类加载器加载同一个类，从而产生多个实例，所以，应尽量保证多个类加载器不会
     * 装载同一个单例类
     */
    private static Singleton singleton = null;
    private static Object obj = new Object();

    private Singleton(){}

    public static Singleton getInstance(){
        if (singleton == null){
            synchronized (obj){
                if (singleton == null){
                    singleton = new Singleton();
                }
            }
        }
        return singleton;
    }
}
