package com.demo.thread;

import java.util.concurrent.locks.ReentrantLock;

import com.fc.constant.NormalNumberConstant;

/**
 * @author fangcong on 2017/6/13.
 * 重入锁
 */
public class ReenterLock implements Runnable{

    private static ReentrantLock lock = new ReentrantLock();
    private static ReenterLock instance = new ReenterLock();
    private static int i = 0;

    public static synchronized void increase() {
        i++;
    }

    @Override
    public void run() {
        for (int j = 0; j < NormalNumberConstant.INT_10000; j++) {
            increase();
        }
    }

    public static void main(String[] args) throws InterruptedException{
        Thread t1 = new Thread(instance);
        Thread t2 = new Thread(instance);
        t1.start();
        t2.start();
        t1.join();
        t2.join();
        System.out.println(i);
    }
}
