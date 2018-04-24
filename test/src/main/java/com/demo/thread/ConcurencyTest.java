package com.demo.thread;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

/**
 * @author fangcong on 2018/4/20.
 */
public class ConcurencyTest {

    public static void main(String[] args) throws Exception {
        int[] loops = {10000, 100000, 1000000, 10000000};
        /*for (int i = 0; i < loops.length; i++) {
            concurrency(loops[i]);
            serial(loops[i]);
            System.out.println("=======================");
        }*/
        Map<String, Object> map = new HashMap<>();
        map.put("flag", true);
        Map<String, Object> map1 = new HashMap<>();
        map1.put("list", Arrays.asList(loops));
        map.put("map", map1);

        for (Entry entry : map.entrySet()) {
            System.out.println("key:" + entry.getKey());
            System.out.println("value:" + entry.getValue().toString());
        }
    }

    /**
     * 并发执行
     * @throws InterruptedException
     */
    private static void concurrency(int loop) throws InterruptedException {
        long start = System.currentTimeMillis();
        Thread thread = new Thread(()->{
           for (int i = 0; i < loop; i++) {
               int a = 0;
               a = a + 5;
           }
        });
        thread.start();
        int b = 0;
        for (int i = 0; i < loop; i++) {
            b--;
        }
        long time = System.currentTimeMillis();
        thread.join();
        System.out.println("cost:" + (time - start) + " and b = " + b);
    }

    /**
     * 串行计算
     */
    private static void serial(int loop) {
        long start = System.currentTimeMillis();
        for (int i = 0; i < loop; i++) {
            int a = 0;
            a = a + 5;
        }
        int b = 0;
        for (int i = 0; i < loop; i++) {
            b--;
        }
        long time = System.currentTimeMillis();
        System.out.println("cost:" + (time - start) + " and b = " + b);
    }
}
