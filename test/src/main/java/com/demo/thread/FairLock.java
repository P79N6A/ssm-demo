package com.demo.thread;

import java.util.concurrent.locks.ReentrantLock;

/**
 * @author fangcong on 2018/3/19.
 */
public class FairLock implements Runnable {

    private static ReentrantLock lock = new ReentrantLock(true);

    @Override
    public void run() {
        while (true) {
            try {
                lock.lock();
                System.out.println(Thread.currentThread().getName() + " get lock");
            } finally {
                lock.unlock();
            }
        }
    }

    public static void main(String[] args) {
        FairLock fairLock = new FairLock();
        Thread t1 = new Thread(fairLock, "thread1");
        Thread t2 = new Thread(fairLock, "thread2");
        t1.start();
        t2.start();
    }
}
