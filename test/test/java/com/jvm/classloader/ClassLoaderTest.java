package com.jvm.classloader;

import java.io.IOException;
import java.io.InputStream;

/**
 * @author fangcong on 2017/5/10.
 * 类加载器与instanceof关键字演示
 */
public class ClassLoaderTest {

    /**
     * 1、比较两个类是否“相等”，必须是同一个类加载器加载下才有意义
     * 2、同一虚拟机，不同类加载器加载的类类型比较必然返回false，如下示例
     * @param args
     * @throws Exception
     */
    public static void main(String[] args) throws Exception{
        //自定义类加载器
        ClassLoader myClassLoader = new ClassLoader() {

            @Override
            public Class<?> loadClass(String name) throws ClassNotFoundException {
                try {
                    String fileName = name.substring(name.lastIndexOf(".") + 1) + ".class";
                    InputStream is = getClass().getResourceAsStream(fileName);
                    if (is == null) {
                        return super.loadClass(name);
                    }
                    byte[] bytes = new byte[is.available()];
                    is.read(bytes);
                    return defineClass(name, bytes, 0, bytes.length);
                } catch (IOException e) {
                    throw new ClassNotFoundException(name);
                }
            }
        };
        Object obj = myClassLoader.loadClass("com.jvm.classloader.ClassLoaderTest").newInstance();
        System.out.println(obj.getClass());
        //虽然是同一个类，但由于是不同类加载器加载，因此JVM中存在两个ClassLoaderTest类
        System.out.println(obj instanceof com.jvm.classloader.ClassLoaderTest);
    }
}
