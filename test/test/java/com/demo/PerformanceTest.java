package com.demo;

import java.text.SimpleDateFormat;
import java.util.Date;

import com.demo.thread.DateThreadLocalUtil;

/**
 * @author fangcong on 2017/8/22.
 * 测试threadLocal性能
 */
public class PerformanceTest {

    private static final String DATE_FORMAT = "yyyy-MM-dd HH:mm:ss";

    //单例
    private static final SimpleDateFormat sdf = new SimpleDateFormat(DATE_FORMAT);

    /**
     * 性能对比，普通方式每次都要new对象花费大量时间，改为全局final对象
     * 1、new对象方式：524ms VS 67ms
     * 2、全局fina ：121ms VS 65ms
     * @param args
     */
    public static void main(String[] args) {
        int loop = 100000;
        Date date = new Date();
        long startTime = System.currentTimeMillis();
        for (int i = 0;i < loop;i++) {
            sdf.format(date);
        }
        long end = System.currentTimeMillis();
        System.out.println("normal : " + (end - startTime) + "ms");

        startTime = System.currentTimeMillis();
        for (int i = 0;i < loop;i++) {
            DateThreadLocalUtil.format(date);
        }
        end = System.currentTimeMillis();
        System.out.println("threadLocal : " + (end - startTime) + "ms");
    }
}
