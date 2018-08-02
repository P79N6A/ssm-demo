package com.demo.queue;

import java.util.concurrent.BlockingQueue;

/**
 * 消费者线程
 *
 * @author fangcong
 */
public class Consumer implements Runnable {

    private BlockingQueue<Apple> queue;

    Consumer(BlockingQueue<Apple> blockingQueue) {
        this.queue = blockingQueue;
    }

    @Override
    public void run() {
        try {
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
