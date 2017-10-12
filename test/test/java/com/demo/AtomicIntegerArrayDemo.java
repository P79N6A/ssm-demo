package com.demo;

import java.util.concurrent.atomic.AtomicIntegerArray;

/**
 * @author fangcong on 2016/9/21.
 */
public class AtomicIntegerArrayDemo {

    private static AtomicIntegerArray arr = new AtomicIntegerArray(10);

    public static class AddThread implements Runnable{

        @Override
        public void run() {
            int loop = 10000;
            for (int k = 0;k < loop;k++){
                arr.getAndIncrement(k%arr.length());
            }
        }
    }

    public static void main(String[] args) throws InterruptedException {
        int size = 10;
        Thread[] thread = new Thread[size];
        for (int k = 0;k < size;k++){
            thread[k] = new Thread(new AddThread());
        }
        for (int k = 0;k < size;k++){
            thread[k].start();
        }
        for (int k = 0;k < size;k++){
            thread[k].join();
        }
        System.out.println(arr);
    }
}
