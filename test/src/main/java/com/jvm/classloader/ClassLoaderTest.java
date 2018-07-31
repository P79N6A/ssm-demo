package com.jvm.classloader;

import java.io.IOException;
import java.io.InputStream;

/**
 * @author fangcong on 2017/5/10.
 *         类加载器与instanceof关键字演示
 */
public class ClassLoaderTest {

    public static class SsClass {
        static {
            System.out.println("SsClass init.");
        }
    }

    public static class SuperClass extends SsClass {
        static {
            System.out.println("SuperClass init.");
        }

        public static int value = 123;

        public SuperClass() {
            System.out.println("init SuperClass.");
        }
    }

    public static class SubClass extends SuperClass {
        static {
            System.out.println("SubClass init.");
        }

        static int n;

        public SubClass() {
            System.out.println("init SubClass.");
        }
    }

    /**
     * 常量在编译阶段会存入常量池中，不会触发所在类的初始化
     */
    public static class ConstantClass {
        static {
            System.out.println("ConstantClass init.");
        }

        private static final String HELLO_WORLD = "hello world!";
    }

    /**
     * 1、比较两个类是否“相等”，必须是同一个类加载器加载下才有意义
     * 2、同一虚拟机，不同类加载器加载的类类型比较必然返回false，如下示例
     *
     * @param args
     * @throws Exception
     */
    public static void main(String[] args) throws Exception {
        // 子类引入父类的静态字段，只会触发父类的初始化
        System.out.println(SubClass.value);
        System.out.println(ConstantClass.HELLO_WORLD);
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
        System.out.println(myClassLoader.toString());
        // 类加载器层级结构：自定义-应用类-扩展类-启动类
        ClassLoader appClassLoader = myClassLoader.getParent();
        ClassLoader extensionClassLoader = appClassLoader.getParent();
        ClassLoader bootStrapClassLoader = extensionClassLoader.getParent();
        System.out.println(appClassLoader.toString());
        System.out.println(extensionClassLoader.toString());
        System.out.println(bootStrapClassLoader);
        //根据类的全限定类名加载类
        Object obj = myClassLoader.loadClass("com.jvm.classloader.ClassLoaderTest").newInstance();
        System.out.println(obj.getClass());
        //虽然是同一个类，但由于是不同类加载器加载，因此JVM中存在两个ClassLoaderTest类
        System.out.println(obj instanceof com.jvm.classloader.ClassLoaderTest);
    }
}

class StaticTest {
    public static void main(String[] args) {
        staticFunction();
    }

    static StaticTest st = new StaticTest();

    static {
        System.out.println("1");
    }

    {
        System.out.println("2");
    }

    StaticTest() {
        System.out.println("3");
        System.out.println("a=" + a + ",b=" + b);
    }

    public static void staticFunction() {
        System.out.println("4");
    }

    int a = 110;
    static int b = 112;
}
