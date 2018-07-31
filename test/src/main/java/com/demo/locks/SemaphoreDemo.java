package com.demo.locks;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;

/**
 * 信号量，控制最大并发线程数量
 *
 * @author fangcong on 2018/4/27.
 */
public class SemaphoreDemo {

    private static final int MAX_THREAD = 30;

    /**
     * 线程池最大允许30个线程同时执行
     */
    private static final ExecutorService EXECUTOR_SERVICE = Executors.newFixedThreadPool(MAX_THREAD);

    public static void main(String[] args) {
        //控制同时最多10个线程执行
        Semaphore semaphore = new Semaphore(10);
        for (int i = 0; i < MAX_THREAD; i++) {
            EXECUTOR_SERVICE.execute(() -> {
                try {
                    semaphore.acquire();
                    System.out.println("work : " + Thread.currentThread().getId() + " at time : "
                        + System.currentTimeMillis());
                    semaphore.release();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            });
        }
        EXECUTOR_SERVICE.shutdown();
    }
}
