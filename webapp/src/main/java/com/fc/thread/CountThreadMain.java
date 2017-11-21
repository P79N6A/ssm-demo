package com.fc.thread;

/**
 * @author fangcong on 2017/4/1.
 */
public class CountThreadMain implements Runnable{

    int num = 100;

    public synchronized void m1() throws InterruptedException {
        num = 1000;
        Thread.sleep(500);
        System.out.println("m1 num = " + num);
    }

    public synchronized void m2() throws InterruptedException {
        Thread.sleep(250);
        num = 2000;
    }

    public static void main(String[] args) throws InterruptedException {
        CountThreadMain threadMain = new CountThreadMain();
        Thread t = new Thread(threadMain);
        t.start();

        threadMain.m2();

        System.out.println("final num = " + threadMain.num);
    }

    @Override
    public void run() {
        try {
            m1();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
