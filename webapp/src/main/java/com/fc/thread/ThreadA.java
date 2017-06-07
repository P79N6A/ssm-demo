package com.fc.thread;

/**
 * Created by fangcong on 2017/4/1.
 */
public class ThreadA extends Thread {

    private Count count;

    public ThreadA(Count count) {
        this.count = count;
    }

    @Override
    public void run() {
        count.add();
        count.concurrentAdd();
    }

}
