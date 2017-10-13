package com.fc.designs.singleton;

import java.io.Serializable;
import java.util.concurrent.atomic.AtomicInteger;

import com.fc.constant.NormalNumberConstant;

/**
 * @author angcong on 2017/2/20.
 *         测试volatile能否对变量保持原子性
 */
public class TestVolatile implements Serializable {

    public AtomicInteger inc = new AtomicInteger();

    public void increase() {
        inc.getAndIncrement();
    }

    public static void main(String[] args) {
        final TestVolatile test = new TestVolatile();
        //创建10个线程
        for (int i = 0; i < NormalNumberConstant.INT_10; i++) {
            new Thread() {
                @Override
                public void run() {
                    for (int j = 0; j < NormalNumberConstant.INT_10; j++) {
                        test.increase();
                    }
                }
            }.start();
        }
        System.out.println("thread create over...");
        //所有的线程都执行完
        System.out.println(test.inc);
    }
}
