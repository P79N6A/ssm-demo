package com.demo.thread;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author fangcong on 2018/3/19.
 */
public class JdkThreadPoolDemo {

    public static void main(String[] args) throws InterruptedException {
        MyTask task = new MyTask();
        // ExecutorService service = Executors.newFixedThreadPool(5);
        ExecutorService service = Executors.newCachedThreadPool();
        for (int i = 0; i < 10; i++) {
            service.submit(task);
        }
        service.shutdown();

        AtomicInteger i = new AtomicInteger(0);
        ScheduledExecutorService scheduledExecutorService = Executors.newScheduledThreadPool(10);
        scheduledExecutorService.scheduleAtFixedRate(()->{
            try {
                Thread.sleep(2000);
                i.getAndIncrement();
                System.out.println(System.currentTimeMillis() / 1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }, 0, 1, TimeUnit.SECONDS);
        while (true) {
            if (i.intValue() == 10) {
                scheduledExecutorService.shutdown();
                break;
            }
        }
    }

    public static class MyTask implements Runnable {

        @Override
        public void run() {
            System.out.println(System.currentTimeMillis() + ":Thread ID:" + Thread.currentThread().getId());
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}

