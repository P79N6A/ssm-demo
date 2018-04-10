package com.demo.future.parallel;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

/**
 * @author fangcong on 2018/4/10.
 */
public class SortWorkDemo {

    static class TaskA implements Runnable {

        public static BlockingQueue<String> queue = new LinkedBlockingQueue<>();

        @Override
        public void run() {
            try {
                String task = queue.take();
                System.out.println("a处理" + task + "任务");
                TaskB.queue.add("B");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    static class TaskB implements Runnable {
        public static BlockingQueue<String> queue = new LinkedBlockingQueue<>();

        @Override
        public void run() {
            try {
                String task = queue.take();
                System.out.println("b处理" + task + "任务");
                TaskC.queue.add("C");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    static class TaskC implements Runnable {
        public static BlockingQueue<String> queue = new LinkedBlockingQueue<>();

        @Override
        public void run() {
            try {
                String task = queue.take();
                System.out.println("c处理" + task + "任务");
                TaskD.queue.add("D");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    static class TaskD implements Runnable {
        public static BlockingQueue<String> queue = new LinkedBlockingQueue<>();

        @Override
        public void run() {
            try {
                String task = queue.take();
                System.out.println("d处理" + task + "任务");
                TaskE.queue.add("E");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    static class TaskE implements Runnable {
        public static BlockingQueue<String> queue = new LinkedBlockingQueue<>();

        @Override
        public void run() {
            try {
                String task = queue.take();
                System.out.println("e处理" + task + "任务");
                System.out.println("任务处理完毕");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public static void main(String[] args) {
        new Thread(new TaskA()).start();
        new Thread(new TaskB()).start();
        new Thread(new TaskC()).start();
        new Thread(new TaskD()).start();
        new Thread(new TaskE()).start();

        TaskA.queue.add("A");
    }
}
