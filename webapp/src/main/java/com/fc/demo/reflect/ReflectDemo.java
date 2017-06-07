package com.fc.demo.reflect;

import java.lang.reflect.Method;

/**
 * Created by fangcong on 2017/4/7.
 */
public class ReflectDemo {

    public static void callHiddenMethod(Object obj, String methodName) throws Exception{
        Method method = obj.getClass().getDeclaredMethod(methodName);
        method.setAccessible(true);
        method.invoke(obj);
    }

    public static void main(String[] args) throws Exception{
        A a = HiddenA.makeA();
        a.f();
        System.out.println(a.getClass().getName());

        callHiddenMethod(a, "g");
        callHiddenMethod(a, "u");
        callHiddenMethod(a, "v");
        callHiddenMethod(a, "w");
    }
}
