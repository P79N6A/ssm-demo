package com.fc.designs.proxy;

/**
 * 代理类，拥有被代理类的句柄
 *
 * @author fangcong on 2018/4/10.
 */
public class WorkProxy implements Work {

    private String worker;

    private WorkImp workImp;

    public WorkProxy(String worker) {
        this.worker = worker;
    }

    @Override
    public void doWork() {
        if (null == workImp) {
            workImp = new WorkImp(worker);
        }
        System.out.print("begin proxy : ");
        workImp.doWork();
    }
}
