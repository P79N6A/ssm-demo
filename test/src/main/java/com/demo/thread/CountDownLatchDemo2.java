package com.demo.thread;

import java.util.Random;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;

import com.fc.common.ThreadPoolUtils;

/**
 * @author fangcong on 2018/3/19.
 */
public class CountDownLatchDemo2 implements Runnable {

    private static final CountDownLatch LATCH = new CountDownLatch(10);

    private static final CountDownLatchDemo2 DEMO_2 = new CountDownLatchDemo2();

    @Override
    public void run() {
        try {
            Thread.sleep(new Random().nextInt(10) * 1000);
            System.out.println("check complete");
            LATCH.countDown();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) throws InterruptedException {
        ExecutorService service = ThreadPoolUtils.getThreadFactoryPool(10);
        for (int i = 0; i < 10; i++) {
            service.submit(DEMO_2);
        }
        LATCH.await();
        System.out.println("fire");
        service.shutdown();
    }
}
