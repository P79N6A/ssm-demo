package com.exams;

import java.util.concurrent.CompletableFuture;

/**
 * @author fangcong on 2018/4/11.
 */
public class AskThread implements Runnable {

    CompletableFuture<Integer> future = null;

    public AskThread(CompletableFuture<Integer> future) {
        this.future = future;
    }

    @Override
    public void run() {
        int result = 0;
        try {
            result = future.get() * future.get();
        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println(result);
    }

    public static Integer cal(int num) {
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        int res = num * num;
        System.out.println("cal result : " + res);
        return res;
    }

    public static void main(String[] args) throws Exception {
        final CompletableFuture<Integer> future = new CompletableFuture<>();
        new Thread(new AskThread(future)).start();

        Thread.sleep(1000);

        future.complete(60);

        final CompletableFuture<Integer> future1 = CompletableFuture.supplyAsync(() -> cal(50));
        //get等待调用操作，目的是等待cal()执行完成
        System.out.println("future result : " + future1.get());
    }
}
