package com.demo.thread;

import java.util.Random;
import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;

/**
 * @author fangcong on 2018/3/19.
 */
public class CyclicBarrierDemo {

    public static class Soldier implements Runnable {

        /**
         * 士兵
         */
        private String soldier;

        /**
         * 计数器
         */
        private final CyclicBarrier cyclicBarrier;

        public Soldier(CyclicBarrier cyclicBarrier, String soldier) {
            this.cyclicBarrier = cyclicBarrier;
            this.soldier = soldier;
        }

        @Override
        public void run() {
            try {
                cyclicBarrier.await();
                doWork();
                cyclicBarrier.await();
            } catch (InterruptedException e) {
                e.printStackTrace();
            } catch (BrokenBarrierException e) {
                e.printStackTrace();
            }
        }

        public void doWork() {
            try {
                Thread.sleep(new Random().nextInt(5) * 1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(soldier + " : 任务完成！");
        }
    }

    public static class BarrierRun implements Runnable {

        boolean flag;
        int n;

        public BarrierRun(boolean flag, int n) {
            this.flag = flag;
            this.n = n;
        }

        @Override
        public void run() {
            if (flag) {
                System.out.println("士兵" + n + "个任务完成！");
            } else {
                System.out.println("士兵" + n + "个集合完毕");
                flag = true;
            }
        }
    }

    public static void main(String[] args) {
        final int N = 10;
        Thread[] threads = new Thread[N];
        boolean flag = false;

        CyclicBarrier cyclicBarrier = new CyclicBarrier(N, new BarrierRun(flag, N));
        System.out.println("集合完毕！");

        for (int i = 0; i < N; i++) {
            System.out.println("士兵" + i + "报道!");
            threads[i] = new Thread(new Soldier(cyclicBarrier, "士兵" + i));
            threads[i].start();
        }
    }
}
