package com.demo.thread;

import java.util.concurrent.locks.ReentrantLock;

/**
 * @author fangcong on 2017/6/13.
 * 重入锁
 */
public class ReenterLock implements Runnable{

    private static ReentrantLock lock = new ReentrantLock();
    private static int i = 0;

    @Override
    public void run() {
        for (int j = 0; j < 100000; j++) {
            lock.lock();
            try {
                i++;
            } finally {
                lock.unlock();
            }
        }
    }

    public static void main(String[] args) throws InterruptedException{
        ReenterLock t = new ReenterLock();
        Thread t1 = new Thread(t);
        Thread t2 = new Thread(t);
        t1.start();
        t2.start();
        t1.join();
        t2.join();
        System.out.println(i);
    }
}
