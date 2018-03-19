package com.fc.thread;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author fangcong on 2018/3/16.
 */
public class BadLockInstance implements Runnable{

    public static Integer i = 0;

    public static AtomicInteger a = new AtomicInteger(0);

    static BadLockInstance instance = new BadLockInstance();

    @Override
    public void run() {
        for (int j = 0; j < 100000; j++) {
            synchronized (BadLockInstance.class) {
                i++;
            }
            a.incrementAndGet();
        }
    }

    public static void main(String[] args) throws InterruptedException {
        Thread t1 = new Thread(instance);
        Thread t2 = new Thread(instance);
        t1.start();
        t2.start();
        t1.join();
        t2.join();
        System.out.println("i = " + i);
        System.out.println("a = " + a);
    }
}
