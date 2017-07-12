package com.demo.thread;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author fangcong on 2017/7/6.
 */
public class ReenTrantLockCondition implements Runnable {

    private static ReentrantLock lock = new ReentrantLock();
    private static Condition condition = lock.newCondition();

    @Override
    public void run() {
        try {
            lock.lock();
            condition.await();
            System.out.println("this is going on");
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }

    public static void main(String[] args) throws InterruptedException{
        ReenTrantLockCondition trantLockCondition = new ReenTrantLockCondition();
        Thread t = new Thread(trantLockCondition);
        t.start();
        Thread.sleep(2000);
        //通知线程继续执行
        lock.lock();
        condition.signal();
        lock.unlock();
    }
}
