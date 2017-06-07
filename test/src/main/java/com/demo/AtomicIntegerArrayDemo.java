package com.demo;

import java.util.concurrent.atomic.AtomicIntegerArray;

/**
 * Created by fangcong on 2016/9/21.
 */
public class AtomicIntegerArrayDemo {

    private static AtomicIntegerArray arr = new AtomicIntegerArray(10);

    public static class AddThread implements Runnable{

        @Override
        public void run() {
            for (int k = 0;k < 10000;k++){
                arr.getAndIncrement(k%arr.length());
            }
        }
    }

    public static void main(String[] args) throws InterruptedException {
        Thread[] thread = new Thread[10];
        for (int k = 0;k < 10;k++){
            thread[k] = new Thread(new AddThread());
        }
        for (int k = 0;k < 10;k++){
            thread[k].start();
        }
        for (int k = 0;k < 10;k++){
            thread[k].join();
        }
        System.out.println(arr);
    }
}
