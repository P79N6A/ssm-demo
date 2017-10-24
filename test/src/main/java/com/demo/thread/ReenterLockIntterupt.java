package com.demo.thread;

import java.util.concurrent.locks.ReentrantLock;

/**
 * @author fangcong on 2017/7/5.
 */
public class ReenterLockIntterupt implements Runnable {

    private static ReentrantLock lock1 = new ReentrantLock();
    private static ReentrantLock lock2 = new ReentrantLock();
    private int lock;

    /**
     * 控制加锁顺序，方便死锁
     * @param lock
     */
    public ReenterLockIntterupt(int lock) {
        this.lock = lock;
    }

    @Override
    public void run() {
        try {
            if (lock == 1) {
                lock1.lockInterruptibly();
                Thread.sleep(500);
                lock2.lockInterruptibly();
            } else {
                lock2.lockInterruptibly();
                Thread.sleep(500);
                lock1.lockInterruptibly();
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            if (lock1.isHeldByCurrentThread()) {
                lock1.unlock();
            }
            if (lock2.isHeldByCurrentThread()) {
                lock2.unlock();
            }
            System.out.println(Thread.currentThread().getId() + ":线程退出");
        }
    }

    public static void main(String[] args) throws InterruptedException{
        ReenterLockIntterupt inLock1 = new ReenterLockIntterupt(1);
        ReenterLockIntterupt inLock2 = new ReenterLockIntterupt(2);
        Thread t1 = new Thread(inLock1);
        Thread t2 = new Thread(inLock2);
        t1.start();
        t2.start();

        Thread.sleep(1000);

        t2.interrupt();
    }
}
