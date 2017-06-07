package com.jvm.dispatcher;

/**
 * @author fangcong on 2017/5/10.
 * 静态分派，根据运行时参数类型重载
 */
public class StaticDispatcher {

    static abstract class Human {}

    static class Man extends Human {}

    static class Women extends Human {}

    public void sayHello(Human human) {
        System.out.println("human");
    }

    public void sayHello(Man man) {
        System.out.println("man");
    }

    public void sayHello(Women women) {
        System.out.println("women");
    }

    public static void main(String[] args) {
        //定义静态类型相同但实际类型不同的两个对象
        Human man = new Man();
        Human women = new Women();

        StaticDispatcher sd = new StaticDispatcher();
        //重载时根据参数的静态类型作为判定依据
        sd.sayHello(man);
        sd.sayHello(women);
    }
}
