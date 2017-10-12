package com.fc.demo;

/**
 * Created by fangcong on 2017/2/22.
 */
public class TestExtends {

    static class A{}

    static class B extends A{}

    static class C extends B{}

    public static void main(String[] args){
        String fileName = "洋河端午互动说明.pptx";
        String suffix = fileName.substring(fileName.lastIndexOf("."));
        String tfsName = fileName.substring(0, fileName.lastIndexOf("."));
        System.out.println(suffix);
        System.out.println(tfsName);
    }
}
