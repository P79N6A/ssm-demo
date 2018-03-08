package com.demo.queue;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.LinkedBlockingQueue;

/**
 * @author fangcong on 2018/3/8.
 */
public class LinkedBlockingQueueDemo {

    private static final LinkedBlockingQueue<Apple> QUEUE = new LinkedBlockingQueue<>(15);

    public static void main(String[] args) {
        ExecutorService service = Executors.newCachedThreadPool();

        for (int i = 0; i < 20; i++) {
            service.submit(new Producer(QUEUE));
        }

        for (int j = 0; j < 20; j++) {
            service.submit(new Consumer(QUEUE));
        }

        service.shutdown();
    }
}
