package com.demo.queue;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.TimeUnit;

/**
 * ArrayBlockingQueue实现生产者消费者模型
 *
 * @author fangcong on 2018/3/6.
 */
public class ArrayBlockingQueueDemo {

    private static final ArrayBlockingQueue<Apple> APPLE_ARRAY_BLOCKING_QUEUE = new ArrayBlockingQueue<>(1);

    public static void main(String[] args) {
        new Thread(new Producer(APPLE_ARRAY_BLOCKING_QUEUE)).start();
        new Thread(new Producer(APPLE_ARRAY_BLOCKING_QUEUE)).start();
        new Thread(new Consumer(APPLE_ARRAY_BLOCKING_QUEUE)).start();
        new Thread(new Consumer(APPLE_ARRAY_BLOCKING_QUEUE)).start();
    }
}

/**
 * 生产消费模型
 */
class Apple {
    public Apple() {}
}

/**
 * 生产者线程
 */
class Producer implements Runnable {
    private ArrayBlockingQueue<Apple> appleArrayBlockingQueue;

    Producer(ArrayBlockingQueue<Apple> queue) {
        this.appleArrayBlockingQueue = queue;
    }

    @Override
    public void run() {
        while (true) {
            produce();
        }
    }

    private void produce() {
        try {
            Apple apple = new Apple();
            //插入队列尾部，若队列已满则阻塞
            appleArrayBlockingQueue.put(apple);
            System.out.println("生产：" + apple);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}

/**
 * 消费者线程
 */
class Consumer implements Runnable {

    private ArrayBlockingQueue<Apple> queue;

    Consumer(ArrayBlockingQueue<Apple> appleArrayBlockingQueue) {
        this.queue = appleArrayBlockingQueue;
    }

    @Override
    public void run() {
        try {
            TimeUnit.SECONDS.sleep(1);
            consume();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    private void consume() throws InterruptedException {
        //移除队列头，没有元素则阻塞
        Apple apple = queue.take();
        System.out.println("消费:" + apple);
    }
}


