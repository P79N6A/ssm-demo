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

    /**
     * SimpleDateFormat是线程不安全的类，不定义未static变量，示例用法1如下：
     * @param date
     * @return
     */
    public static String getDateFormat(Date date) {
        SimpleDateFormat sdf = new SimpleDateFormat(DATE_FORMAT);
        return sdf.format(date);
    }

    /**
     * 性能对比，普通方式每次都要new对象花费大量时间，改为全局final对象
     * 1、new对象方式：524ms VS 67ms
     * 2、全局fina ：121ms VS 65ms
     * @param args
     */
    public static void main(String[] args) {
        int loop = 100000;
        long startTime = System.currentTimeMillis();
        Date date = new Date();
        for (int i = 0;i < loop;i++) {
            getDateFormat(date);
        }
        long end = System.currentTimeMillis();
        System.out.println("normal : " + (end - startTime) + "ms");

        startTime = System.currentTimeMillis();
        for (int i = 0;i < loop;i++) {
            DateThreadLocalUtil.format(date);
        }
        DateThreadLocalUtil.remove();
        end = System.currentTimeMillis();
        System.out.println("threadLocal : " + (end - startTime) + "ms");
    }
}
