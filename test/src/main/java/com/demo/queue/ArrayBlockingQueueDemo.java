package com.demo.queue;

import java.util.concurrent.ArrayBlockingQueue;

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


