package com.demo.future.parallel;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Stream;

/**
 * @author fangcong on 2018/10/26.
 */
public class ConcatVedioDemo {

    public static class VedioA implements Runnable {

        public static final String[] A = {"a1", "a2", "a3", "a4"};

        @Override
        public void run() {
            Stream.of(A).forEach(a -> VedioB.bq.add(a));
        }
    }

    public static class VedioB implements Runnable {

        public static final String[] B = {"b1", "b2"};

        public static BlockingQueue<String> bq = new LinkedBlockingQueue<>();

        @Override
        public void run() {
            while (true) {
                try {
                    String resultA = bq.take();
                    Stream.of(B).forEach(b -> {
                        try {
                            Thread.sleep(3000);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                        System.out.println("resultB : " + resultA + b);
                        VedioC.cq.add(resultA + b);
                    });
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public static class VedioC implements Runnable {

        public static final String[] C = {"c1", "c2", "c3", "c4", "c5"};

        public static BlockingQueue<String> cq = new LinkedBlockingQueue<>();

        private static AtomicInteger count = new AtomicInteger(0);

        @Override
        public void run() {
            while (true) {
                try {
                    String resultB = cq.take();
                    Stream.of(C).forEach(c -> {
                        try {
                            Thread.sleep(10000);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                        System.out.println("resultC : " + resultB + c);
                        System.out.println("c = " + count.incrementAndGet());
                    });
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public static void main(String[] args) {
        Thread threadA = new Thread(new VedioA());
        Thread threadB = new Thread(new VedioB());
        Thread threadC = new Thread(new VedioC());

        threadA.start();
        threadB.start();
        threadC.start();

        System.out.println("执行中...");
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }
}
