package com.fc.common;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

import com.ali.com.google.common.util.concurrent.ThreadFactoryBuilder;
import com.fc.constant.NormalNumberConstant;

/**
 * @author fangcong on 2017/6/16.
 * 创建线程池的简单示例
 */
public class ThreadPoolDemo {

    /**
     * 定义静态内部线程类
     */
    public static class MyTask implements Runnable {

        @Override
        public void run() {
            System.out.println(System.currentTimeMillis() + ":Thread name:"
                + Thread.currentThread().getName());
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * 获取线程池
     * @return
     */
    public static ExecutorService getThreadFactoryPool() {
        ThreadFactory namedThreadFactory = new ThreadFactoryBuilder()
            .setNameFormat("demo-pool-%d").build();
        ExecutorService executorService = new ThreadPoolExecutor(5, 10,
            0L, TimeUnit.MILLISECONDS,
            new LinkedBlockingDeque<>(1024), namedThreadFactory, new ThreadPoolExecutor.AbortPolicy());
        return executorService;
    }

    /**
     * @param args
     */
    public static void main(String[] args) {
        MyTask myTask = new MyTask();
        ExecutorService executorService = getThreadFactoryPool();
        for (int i = 0; i < NormalNumberConstant.INT_10; i++) {
            executorService.execute(myTask);
        }
        executorService.shutdown();
    }
}
