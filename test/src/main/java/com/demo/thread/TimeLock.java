package com.demo.thread;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author fangcong on 2018/3/19.
 */
public class TimeLock implements Runnable {

    private static ReentrantLock reentrantLock = new ReentrantLock();

    private static final Integer LOCK_TIME = 5;

    @Override
    public void run() {
        try {
            if (reentrantLock.tryLock(LOCK_TIME, TimeUnit.SECONDS)) {
                Thread.sleep(6000);
            } else {
                System.out.println("get lock failed");
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            if (reentrantLock.isHeldByCurrentThread()) {
                reentrantLock.unlock();
            }
        }
    }

    public static void main(String[] args) {
        TimeLock timeLock = new TimeLock();
        Thread t1 = new Thread(timeLock);
        Thread t2 = new Thread(timeLock);

        t1.start();
        t2.start();
    }
}
