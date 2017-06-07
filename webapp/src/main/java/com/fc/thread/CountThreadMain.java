package com.fc.thread;

/**
 * Created by fangcong on 2017/4/1.
 */
public class CountThreadMain {

    public static void main(String[] args) {
        Count count = new Count();
        for (int i = 0; i < 5; i++) {
            ThreadA task = new ThreadA(count);
            task.start();
        }

        try {
            Thread.sleep(100L);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("final num = " + count.num);
        System.out.println("final sum = " + count.sum);
    }
}
