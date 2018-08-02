package com.demo.queue;

import java.util.concurrent.BlockingQueue;

/**
 * 生产者线程
 *
 * @author fangcong
 */
public class Producer implements Runnable {

    private BlockingQueue<Apple> blockingQueue;

    Producer(BlockingQueue<Apple> queue) {
        this.blockingQueue = queue;
    }

    @Override
    public void run() {
        produce();
    }

    private void produce() {
        try {
            Thread.sleep(500);
            Apple apple = new Apple();
            //插入队列尾部，若队列已满则阻塞
            blockingQueue.put(apple);
            System.out.println("生产：" + apple);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
