package com.demo.future.jdkFuture;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.FutureTask;

/**
 * @author fangcong on 2018/4/8.
 */
public class FutureMain {

    public static void main(String[] args) throws Exception {
        FutureTask<String> task = new FutureTask<>(new RealData("a"));
        ExecutorService service = Executors.newFixedThreadPool(1);

        service.submit(task);
        System.out.println("请求完毕");

        Thread.sleep(2000);

        System.out.println("data:" + task.get());
    }
}
