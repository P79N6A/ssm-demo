package com.fc.designs.proxy;

/**
 * @author fangcong on 2018/4/10.
 */
public class ProxyDesignDemo {

    public static void main(String[] args) {
        Work work = new WorkProxy("worker1");
        work.doWork();

        work.doWork();
    }
}
