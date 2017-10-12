package com.fc.thread;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * Created by fangcong on 2017/4/6.
 * 利用循环CAS实现原子操作
 * CAS:Compare And Set 比较并交换
 */
public class Counter {

    /**
     * 定义成员变量，原子变量和普通变量
     */
    private AtomicInteger atomicI = new AtomicInteger(0);
    private int i = 0;

    /**
     * 使用CAS实现线程安全计数器
     */
    private void safeCount() {
        for (;;) {
            int j = atomicI.get();
            boolean suc = atomicI.compareAndSet(j, ++j);
            if (suc) {
                break;
            }
        }
    }

    /**
     * 非线程安全计数器
     */
    private void count() {
        i++;
    }

    public static void main(String[] args) {
        final Counter counter = new Counter();
        List<Thread> list = new ArrayList<>(500);
        long start = System.currentTimeMillis();
        int count = 100;
        for (int j = 0;j < count;j++) {
            Thread t = new Thread(new Runnable() {
                @Override
                public void run() {
                    int loop = 10000;
                    for (int k = 0;k < loop;k++) {
                        counter.count();
                        counter.safeCount();
                    }
                }
            });
            list.add(t);
        }
        //启动线程
        for (Thread t : list) {
            t.start();
        }
        //等待所有线程执行完成
        for (Thread t : list) {
            try {
                t.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        System.out.println(counter.i);
        System.out.println(counter.atomicI);
        System.out.println(System.currentTimeMillis() - start);
    }
}
