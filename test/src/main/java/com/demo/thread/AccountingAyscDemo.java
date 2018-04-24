package com.demo.thread;

/**
 * @author fangcong on 2017/7/4.
 */
public class AccountingAyscDemo implements Runnable{

    static AccountingAyscDemo instance = new AccountingAyscDemo();

    static int i = 0;

    public /*synchronized*/ void increase() {
        i++;
    }

    @Override
    public void run() {
        int loop = 1000000;
        for (int i = 0; i < loop; i++) {
            increase();
        }
        System.out.println(Thread.currentThread().getName() + " running...");
    }

    public static void main(String[] args) throws InterruptedException {
        for (int i = 0; i < 4; i++) {
            Thread t = new Thread(instance, "t" + i);
            t.start();
            t.join();
        }
        Thread e = new Thread(instance, "t5");
        e.start();
        e.join();
        //Thread.sleep(5000);
        System.out.println(i);
    }
}
