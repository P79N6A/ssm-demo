package com.fc.thread;

import java.util.concurrent.Callable;

/**
 * Created by fangcong on 2017/4/5.
 */
public class ThreadImpCallable implements Callable {

    @Override
    public String call() throws Exception {
        try {
            Thread.sleep(100);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("this is thread a");
        return "thread-a";
    }
}
