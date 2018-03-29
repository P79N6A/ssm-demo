package com.demo.thread;

import java.util.concurrent.atomic.AtomicStampedReference;

/**
 * @author fangcong on 2018/3/22.
 */
public class AtomicStampedReferenceDemo {

    static AtomicStampedReference<Integer> stampedReference = new AtomicStampedReference<>(19, 0);

    public static void main(String[] args) {
        //模拟多个后台线程同时更新后台数据，为用户充值
        for (int i = 0; i < 3; i++) {
            final int timeStamp = stampedReference.getStamp();
            new Thread(()-> {
                while (true) {
                    while (true) {
                        Integer m = stampedReference.getReference();
                        if (m < 20) {
                            if (stampedReference.compareAndSet(m, m + 20, timeStamp, timeStamp + 1)) {
                                System.out.println("余额小于20元，充值成功！余额：" + stampedReference.getReference());
                                break;
                            }
                        } else {
                            //System.out.println("余额大于20元，无需充值！");
                            break;
                        }
                    }
                }
            }).start();
        }

        //用户消费线程，模拟消费行为
        new Thread(()->{
            for (int i = 0; i < 100; i++) {
                while (true) {
                    int timeStamp = stampedReference.getStamp();
                    Integer money = stampedReference.getReference();
                    if (money > 10) {
                        System.out.println("余额大于10元");
                        if (stampedReference.compareAndSet(money, money - 10, timeStamp, timeStamp + 1)) {
                            System.out.println("成功消费10元，余额：" + stampedReference.getReference());
                            break;
                        }
                    } else {
                        //System.out.println("余额不足");
                        break;
                    }
                }
                try {
                    Thread.sleep(100);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }
}
