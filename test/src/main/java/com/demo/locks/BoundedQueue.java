package com.demo.locks;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * condition demo
 * FIFO方式添加删除元素
 *
 * @author fangcong on 2018/4/25.
 */
public class BoundedQueue<T> {

    private Object[] items;

    private int addIndex, removeIndex, size;

    private Lock lock = new ReentrantLock();

    private Condition notEmpty = lock.newCondition();

    private Condition notFull = lock.newCondition();

    public BoundedQueue(int capacity) {
        items = new Object[capacity];
    }

    /**
     * 添加元素
     *
     * @param t
     * @throws InterruptedException
     */
    public void add(T t) throws InterruptedException {
        lock.lock();
        try {
            //循环代替if是为了防止过早的意外或通知
            while (size == items.length) {
                //添加线程进入等待
                notFull.await();
            }
            items[addIndex] = t;
            if (++addIndex == items.length) {
                addIndex = 0;
            }
            ++size;
            System.out.println("addIndex = " + addIndex + "; size = " + size + "; add T = " + t);
            //删除线程唤醒
            notEmpty.signal();
        } finally {
            lock.unlock();
        }
    }

    /**
     * 删除元素
     *
     * @return
     * @throws InterruptedException
     */
    public T remove() throws InterruptedException {
        lock.lock();
        try {
            while (0 == size) {
                //元素为空时，删除线程进入等待
                notEmpty.await();
            }
            Object e = items[removeIndex];
            if (++removeIndex == items.length) {
                removeIndex = 0;
            }
            --size;
            System.out.println("removeIndex = " + removeIndex + "; size = " + size + "; remove T = " + e);
            //添加线程唤醒
            notFull.signal();
            return (T)e;
        } finally {
            lock.unlock();
        }
    }

    public static void main(String[] args) {
        BoundedQueue<String> boundedQueue = new BoundedQueue<>(10);
        new Thread(() -> {
            for (int i = 0; i < 20; i++) {
                try {
                    boundedQueue.add(String.valueOf(i));
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }).start();

        new Thread(() -> {
            for (int i = 0; i < 20; i++) {
                try {
                    boundedQueue.remove();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }
}
