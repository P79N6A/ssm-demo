package com.fc.demo;

/**
 * Created by fangcong on 2017/2/22.
 */
public class TestExtends {

    static class A{}

    static class B extends A{}

    static class C extends B{}

    public static void main(String[] args){
        //获取jvm是多少位
        /*String a = System.getProperty("sun.arch.data.model");
        String b = System.getProperty("os.arch");
        System.out.println(a);
        System.out.println(b);*/
        //获取java程序使用的内存
        /*long free = Runtime.getRuntime().freeMemory();
        long max = Runtime.getRuntime().maxMemory();
        long total = Runtime.getRuntime().totalMemory();
        System.out.println(free);
        System.out.println(max);
        System.out.println(total);*/
        /*String str1 = new String("abc");
        System.out.println(str1.hashCode());
        String str2 = new String("abc");
        System.out.println(str2.hashCode());
        System.out.println(str1 == str2);
        System.out.println(str1.equals(str2));*/
        /*Random random = new Random();
        int value = random.nextInt(100);
        Long value1 = 90L;
        System.out.println(value);
        System.out.println(value < value1);*/
        String fileName = "洋河端午互动说明.pptx";
        String suffix = fileName.substring(fileName.lastIndexOf("."));
        String tfsName = fileName.substring(0, fileName.lastIndexOf("."));
        System.out.println(suffix);
        System.out.println(tfsName);
    }
}
