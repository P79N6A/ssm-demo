package com.fc.java.improve;

import java.lang.reflect.Field;
import java.lang.reflect.Method;

import com.fc.bean.FieldVO;

/**
 * @author fangcong on 2017/8/11.
 * 测试4种访问修饰符修饰的属性字段访问
 */
public class TestFieldsAccess {

    public static void main(String[] args) {
        try {
            FieldVO fieldVO = new FieldVO();
            fieldVO.setField1("abc");
            fieldVO.setField2(2);
            fieldVO.setField3("ddf");
            fieldVO.setField4(4);
            fieldVO.setField5("afd");
            fieldVO.setField6(6);
            fieldVO.setField7("hkl");
            fieldVO.setField8(8);
            Class clazz = fieldVO.getClass();

            //能获取bean所有定义属性
            System.out.println("getDeclaredFields return :");
            Field[] fields1 = clazz.getDeclaredFields();
            for (Field field : fields1) {
                String getMethodName = "get" + field.getName().substring(0, 1).toUpperCase()
                    + field.getName().substring(1);
                System.out.println(getMethodName);
                Method method = clazz.getDeclaredMethod(getMethodName);
                Object fieldValue = method.invoke(fieldVO);
                System.out.println(fieldValue.toString());
            }

            /*//获取指定名称属性，访问限制和获取所有一致
            Field field1 = clazz.getField("field1");
            System.out.println("getField : " + field1.getName());
            Field field2 = clazz.getDeclaredField("field5");
            System.out.println("getDeclaredField : " + field2.getName());

            //测试获取bean定义方法时，同构方法是否传参返回值
            Method method = clazz.getDeclaredMethod("method1");
            method.invoke(clazz.newInstance());
            Method method1 = clazz.getDeclaredMethod("method1", String.class, Integer.class);
            method1.invoke(clazz.newInstance(), "field2", 11);*/
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
