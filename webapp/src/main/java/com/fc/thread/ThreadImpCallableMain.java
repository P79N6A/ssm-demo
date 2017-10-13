package com.fc.thread;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;

/**
 * @author fangcong on 2017/4/5.
 */
public class ThreadImpCallableMain {

    public static void main(String[] args) {
        ThreadImpCallable thread = new ThreadImpCallable();
        FutureTask<String> task = new FutureTask<>(thread);
        new Thread(task).start();
        System.out.println("main thread begin");
        try {
            System.out.println("return : " + task.get());
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
        System.out.println("main thread end");
    }
}
