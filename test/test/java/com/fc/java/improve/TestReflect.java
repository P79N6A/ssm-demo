package com.fc.java.improve;

import java.lang.reflect.Field;
import java.lang.reflect.Method;

/**
 * Created by fangcong on 2017/3/30.
 */
public class TestReflect {

    private String filed1;

    public void reflect1() {
        System.out.println("test reflect method");
    }

    public void reflect2(String name, Integer age) {
        System.out.println("test reflect method with params:");
        System.out.println("name = " + name + " age = " + age);
    }

    public static void main(String[] args) throws Exception {
        Class<?> clazz = Class.forName("com.fc.java.improve.TestReflect");
        //调用reflect1方法
        Method method1 = clazz.getMethod("reflect1");
        method1.invoke(clazz.newInstance());
        //调用reflect2方法
        Method method2 = clazz.getMethod("reflect2", String.class, Integer.class);
        method2.invoke(clazz.newInstance(), "zhangsan", 22);
        //获取属性
        Field field1 = clazz.getDeclaredField("filed1");
        Object obj = clazz.newInstance();
        field1.setAccessible(true);
        field1.set(obj, "test");
        System.out.println(field1.get(obj));
    }
}
