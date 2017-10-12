package com.fc.thread;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author fangcong on 2017/4/1.
 *         单个干活类Count
 */
public class Count {

    public int num;

    public AtomicInteger sum = new AtomicInteger();

    /**
     * 多线程下访问修改同一变量不安全，可能造成每次值不一致<br>
     * 添加同步关键字解决上述问题
     */
    public synchronized void add() {
        try {
            Thread.sleep(1L);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        num += 1;
        System.out.println(Thread.currentThread().getName() + "*" + num);
    }

    public void concurrentAdd() {
        try {
            Thread.sleep(1L);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        sum.getAndIncrement();
        System.out.println(Thread.currentThread().getName() + "*" + sum);
    }
}
