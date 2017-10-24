package com.demo;

import java.util.concurrent.TimeUnit;

import com.fc.constant.NormalNumberConstant;

/**
 * @author fangcong on 2017/4/21.
 */
public class ThreadJoin {

    public static void main(String[] args) throws Exception{
        Thread previous = Thread.currentThread();
        for (int i = 0; i < NormalNumberConstant.INT_10; i++) {
            Thread thread = new Thread(new Domino(previous), String.valueOf(i));
            thread.start();
            previous = thread;
        }
        TimeUnit.SECONDS.sleep(5);
        System.out.println(Thread.currentThread().getName() + "end");
    }

    static class Domino implements Runnable {

        private Thread thread;

        public Domino(Thread thread) {
            this.thread = thread;
        }

        @Override
        public void run() {
            try {
                thread.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(Thread.currentThread().getName() + "thread");
        }
    }
}
