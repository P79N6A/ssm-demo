package test.test;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author fangcong on 2018/5/4.
 */
public class TestAtomicAndCas {

    static AtomicInteger i = new AtomicInteger();
    static Object obj = new Object();
    static int j;

    public static class AddThread implements Runnable {
        @Override
        public void run() {
            for (int k = 0; k < 10000; k++) {
                i.incrementAndGet();
            }
        }
    }

    public static class AddSynThread implements Runnable {
        @Override
        public void run() {
            synchronized (obj) {
                for (int k = 0; k < 10000; k++) {
                    j++;
                }
            }
        }
    }

    public static void main(String[] args) throws InterruptedException {
        Long startTime = System.currentTimeMillis();
        Thread[] ts = new Thread[100];
        for (int k = 0; k < 100; k++) {
            ts[k] = new Thread(new AddThread());
        }
        for (int i = 0; i < ts.length; i++) {
            ts[i].start();
        }
        for (int i = 0; i < ts.length; i++) {
            ts[i].join();
        }
        Long endTime = System.currentTimeMillis();
        System.out.println("采用原子类通过10个线程对i加10000操作共花费 :" + (endTime - startTime) + "ms");
        System.out.println("10个线程对i进行累加后,结果为: " + i);

        /*//采用Synchronized方式
        startTime = System.currentTimeMillis();
        ts = new Thread[100];
        for (int k = 0; k < 100; k++) {
            ts[k] = new Thread(new AddSynThread());
        }
        for (int k = 0; k < ts.length; k++) {
            ts[k].start();
        }
        for (int k = 0; k < ts.length; k++) {
            ts[k].join();
        }
        endTime = System.currentTimeMillis();
        System.out.println("采用同步锁的方式通过10个线程对j加10000操作共花费 :" + (endTime - startTime) + "ms");
        System.out.println("10个线程加同步锁之后对j进行累加,结果为:" + j);*/
    }
}
