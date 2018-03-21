package com.demo.thread;

import java.util.concurrent.locks.LockSupport;

/**
 * @author fangcong on 2018/3/19.
 */
public class LockSupportDemo {

    public static Object u = new Object();

    static ChangeObjectThread thread1 = new ChangeObjectThread("t1");
    static ChangeObjectThread thread2 = new ChangeObjectThread("t2");

    public static void main(String[] args) throws InterruptedException {
        thread1.start();
        Thread.sleep(100);
        thread2.start();

        LockSupport.unpark(thread1);
        LockSupport.unpark(thread2);

        thread1.join();
        thread2.join();
    }

    public static class ChangeObjectThread extends Thread {

        public ChangeObjectThread(String name) {
            super(name);
        }

        @Override
        public void run() {
            synchronized (u) {
                System.out.println("in " + getName());
                LockSupport.park();
            }
        }
    }
}
