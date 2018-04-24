package com.demo.queue;

import java.util.concurrent.LinkedBlockingQueue;

/**
 * @author fangcong on 2018/3/8.
 */
public class LinkedBlockingQueueDemo {

    private static final LinkedBlockingQueue<Apple> QUEUE = new LinkedBlockingQueue<>(10);

    public static void main(String[] args) {
        /*ExecutorService service = Executors.newCachedThreadPool();

        for (int i = 0; i < 20; i++) {
            service.submit(new Producer(QUEUE));
        }

        for (int j = 0; j < 20; j++) {
            service.submit(new Consumer(QUEUE));
        }

        service.shutdown();*/
        for (int i = 0; i < 100; i++) {
            new Thread(()-> {
                try {
                    if (QUEUE.size() == 10) {
                        QUEUE.take();
                    }
                    Apple apple = new Apple();
                    if (QUEUE.size() < 10) {
                        QUEUE.put(apple);
                    } else {
                        QUEUE.take();
                    }
                    Thread.sleep(100);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }, "thread" + i).start();
        }

        for (int i = 0; i < 10; i++) {
            new Thread(() -> {
                System.out.println(Thread.currentThread().getName() + " read size : " + QUEUE.size());
            }, "read" + i).start();
        }
    }
}
