package com.demo.future.jdk;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.FutureTask;

import com.fc.common.ThreadPoolUtils;

/**
 * FutureTask回调任务
 *
 * @author fangcong on 2018/4/8.
 */
public class FutureMain {

    public static void main(String[] args) throws Exception {
        FutureTask<String> task = new FutureTask<>(new RealData("a"));
        ExecutorService service = ThreadPoolUtils.getThreadFactoryPool(1);

        service.submit(task);
        System.out.println("请求完毕");

        Thread.sleep(2000);

        System.out.println("data:" + task.get());
    }
}
