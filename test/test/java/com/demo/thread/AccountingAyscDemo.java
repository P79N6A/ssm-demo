package com.demo.thread;

/**
 * @author fangcong on 2017/7/4.
 */
public class AccountingAyscDemo implements Runnable{

    static AccountingAyscDemo instance = new AccountingAyscDemo();

    static int i = 0;

    public synchronized void increase() {
        i++;
    }

    @Override
    public void run() {
        for (int i = 0; i < 10000; i++) {
            increase();
        }
    }

    public static void main(String[] args) throws InterruptedException {
        //保证两个线程指向同一个实例，即关注的是同一个对象锁，保证线程安全
        Thread a = new Thread(instance);
        Thread b = new Thread(instance);
        a.start();
        b.start();
        a.join();
        b.join();
        System.out.println(i);
    }
}
