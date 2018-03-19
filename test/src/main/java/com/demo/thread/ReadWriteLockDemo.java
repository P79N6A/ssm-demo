package com.demo.thread;

import java.util.Random;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock.ReadLock;
import java.util.concurrent.locks.ReentrantReadWriteLock.WriteLock;

import com.fc.constant.NormalNumberConstant;

/**
 * @author fangcong on 2017/7/6.
 *         重入锁将使线程串行执行，而读写锁的读操作是并行，将提升执行效率
 */
public class ReadWriteLockDemo {

    /**
     * 定义重入锁，对比性能
     */
    private static Lock lock = new ReentrantLock();

    /**
     * 定义读写锁，测试读写分离操作效率
     */
    private static ReentrantReadWriteLock readWriteLock = new ReentrantReadWriteLock();
    private static ReadLock readLock = readWriteLock.readLock();
    private static WriteLock writeLock = readWriteLock.writeLock();

    /**
     * 全局读写变量
     */
    private int value;

    /**
     * 模拟读操作
     *
     * @param lock 使用何种锁
     * @return
     * @throws InterruptedException
     */
    public Object handleRead(Lock lock) throws InterruptedException {
        try {
            lock.lock();
            Thread.sleep(1000);
            return value;
        } finally {
            lock.unlock();
        }
    }

    /**
     * 模拟写操作
     *
     * @param lock  锁
     * @param index 写入值
     * @throws InterruptedException
     */
    public void handleWrite(Lock lock, int index) throws InterruptedException {
        try {
            lock.lock();
            Thread.sleep(1000);
            value = index;
        } finally {
            lock.unlock();
        }
    }

    /**
     * 创建读写两个线程实例，构建多线程模拟
     *
     * @param args
     */
    public static void main(String[] args) {
        final ReadWriteLockDemo demo = new ReadWriteLockDemo();

        Runnable readRunnable = () -> {
            try {
                int num = (int)demo.handleRead(readLock);
                System.out.println(num);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        };

        Runnable writeRunnable = () -> {
            try {
                demo.handleWrite(writeLock, new Random().nextInt());
                System.out.println("write now ...");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        };

        int readNum = 18;
        for (int i = 0; i < readNum; i++) {
            new Thread(readRunnable).start();
        }

        for (int i = readNum; i < NormalNumberConstant.INT_20; i++) {
            new Thread(writeRunnable).start();
        }
    }
}
