package com.jvm.dispatcher;

import java.io.Serializable;

/**
 * @author fangcong on 2017/5/10.
 * 重载方法匹配优先级
 */
public class OverLoad {

    public static void sayHello(Object args) {
        System.out.println("object");
    }

    public static void sayHello(char args) {
        System.out.println("char");
    }

    public static void sayHello(Character args) {
        System.out.println("character");
    }

    public static void sayHello(int args) {
        System.out.println("int");
    }

    public static void sayHello(long args) {
        System.out.println("long");
    }

    public static void sayHello(char... args) {
        System.out.println("char...");
    }

    public static void sayHello(Serializable args) {
        System.out.println("serializable");
    }

    /**
     * 优先级：char->int->long->Character->Serializable->Object->char...
     * 自动类型转换-->自动装箱-->接口类型的自动转型
     * @param args
     */
    public static void main(String[] args) {
        sayHello('a');
    }
}
