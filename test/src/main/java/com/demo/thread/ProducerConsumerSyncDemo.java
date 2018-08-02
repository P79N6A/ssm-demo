package com.demo.thread;

import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author fangcong on 2018/3/29.
 */
public class ProducerConsumerSyncDemo {

    public static void main(String[] args) throws InterruptedException {
        ProductSyncFactory factory = new ProductSyncFactory(10);
        Thread p1 = new Thread(new ProducerSync(factory), "p1");
        Thread c1 = new Thread(new ConsumerSync(factory), "c1");
        Thread c2 = new Thread(new ConsumerSync(factory), "c2");
        p1.start(); c1.start(); c2.start();
        Thread.sleep(5000);
        p1.interrupt(); c1.interrupt(); c2.interrupt();

        ProductFactoryLock lockFactory = new ProductFactoryLock(10);
        Thread p2 = new Thread(new ProducerSync(lockFactory), "p1");
        Thread c3 = new Thread(new ConsumerSync(lockFactory), "c1");
        Thread c4 = new Thread(new ConsumerSync(lockFactory), "c2");
        p2.start(); c3.start(); c4.start();
    }

}

/**
 * 生产工厂,synchronized实现
 */
class ProductSyncFactory implements ProductFactory {

    private List<String> products;

    private int capcity = 0;

    ProductSyncFactory(int capcity) {
        this.capcity = capcity;
        this.products = new LinkedList<>();
    }

    /**
     * 生产一件产品
     * @param product
     */
    @Override
    public synchronized void produce(String product) {
        while (capcity == products.size()) {
            try {
                System.out.println("警告：线程" + Thread.currentThread().getName() + "准备生产产品，但产品池已满!");
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        products.add(product);
        System.out.println("线程" + Thread.currentThread().getName() + "生产了一件产品：" + product);
        notify();
    }

    /**
     * 消耗一件产品
     * @return
     */
    @Override
    public synchronized void consume() {
        while (0 == products.size()) {
            try {
                System.out.println("警告：线程" + Thread.currentThread().getName() + "准备消耗产品，但产品池已空");
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        String product = products.remove(0);
        System.out.println("线程" + Thread.currentThread().getName() + "消耗了产品:" + product);
        notify();
    }
}

/**
 * 生产者线程
 */
class ProducerSync<T extends ProductFactory> implements Runnable {

    private T factory;

    ProducerSync(T factory) {
        this.factory = factory;
    }

    @Override
    public void run() {
        int i = 0;
        while (true) {
            factory.produce("产品" + i);
            i++;
            try {
                Thread.sleep(10);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}

/**
 * 消费者线程
 */
class ConsumerSync<T extends ProductFactory> implements Runnable {

    private T factory;

    ConsumerSync(T factory) {
        this.factory = factory;
    }

    @Override
    public void run() {
        while (true) {
            factory.consume();
            try {
                Thread.sleep(200);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}

/**
 * 产品工厂，lock实现
 */
class ProductFactoryLock implements ProductFactory {

    /**
     * 产品池
     */
    private List<String> products;

    /**
     * 产品容量
     */
    private int capacity = 0;

    private ReentrantLock reentrantLock = new ReentrantLock();

    /**
     * 生产条件
     */
    private Condition canProduce = reentrantLock.newCondition();

    /**
     * 消费条件
     */
    private Condition canConsume = reentrantLock.newCondition();

    ProductFactoryLock(int capacity) {
        this.capacity = capacity;
    }

    /**
     * 生产一件产品
     * @param product
     */
    @Override
    public void produce(String product) {
        reentrantLock.lock();
        try {
            while (capacity == products.size()) {
                try {
                    System.out.println("警告：线程" + Thread.currentThread().getName() + "准备生产产品，但产品池已满!");
                    canProduce.await();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            products.add(product);
            System.out.println("线程" + Thread.currentThread().getName() + "生产了一件产品：" + product);
            canProduce.signal();
        } finally {
            reentrantLock.unlock();
        }
    }

    /**
     * 消耗一件产品
     * @return
     */
    @Override
    public void consume() {
        reentrantLock.lock();
        try {
            while (0 == products.size()) {
                try {
                    System.out.println("警告：线程" + Thread.currentThread().getName() + "准备消耗产品，但产品池已空");
                    canConsume.await();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            String product = products.remove(0);
            System.out.println("线程" + Thread.currentThread().getName() + "消耗了产品:" + product);
            canConsume.signal();
        } finally {
            reentrantLock.unlock();
        }
    }
}

interface ProductFactory {

    /**
     * 生产方法
     * @param product
     */
    void produce(String product);

    /**
     * 消费方法
     */
    void consume();
}
