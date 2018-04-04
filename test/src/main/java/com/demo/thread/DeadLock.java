package com.demo.thread;

import java.util.concurrent.locks.ReentrantLock;

/**
 * 死锁示例
 *
 * @author fangcong on 2018/4/3.
 */
public class DeadLock extends Thread {

    private ReentrantLock reentrantLock = new ReentrantLock();

    protected Object tool;

    static Object fork1 = new Object();

    static Object fork2 = new Object();

    DeadLock(Object obj) {
        this.tool = obj;
        if (tool.equals(fork1)) {
            this.setName("n1");
        }
        if (tool.equals(fork2)) {
            this.setName("n2");
        }
    }

    @Override
    public void run() {
        if (tool.equals(fork1)) {
            synchronized (fork1) {
                reentrantLock.lock();
                try {
                    reentrantLock.wait(2000);
                } catch (InterruptedException e) {
                    reentrantLock.unlock();
                }
                synchronized (fork2) {
                    System.out.println("n1 get resource");
                }
            }
        }
        if (tool.equals(fork2)) {
            synchronized (fork2) {
                try {
                    Thread.sleep(2000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                synchronized (fork1) {
                    System.out.println("n2 get resource");
                }
            }
        }
    }

    /**
     * n1、n2分别占抢占对方需要的资源而不释放
     *
     * @param args
     * @throws InterruptedException
     */
    public static void main(String[] args) throws InterruptedException {
        DeadLock n1 = new DeadLock(fork1);
        DeadLock n2 = new DeadLock(fork2);
        n1.start();
        n2.start();
        Thread.sleep(1000);
    }
}
