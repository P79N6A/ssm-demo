package com.fc.designs.proxy;

/**
 * 实现类，被代理对象
 *
 * @author fangcong on 2018/4/10.
 */
public class WorkImp implements Work {

    private String worker;

    public WorkImp(String worker) {
        this.worker = worker;
        System.out.println(worker + " join");
    }

    @Override
    public void doWork() {
        System.out.println(worker + " start work");
    }
}
