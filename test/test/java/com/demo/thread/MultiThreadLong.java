package com.demo.thread;

/**
 * @author fangcong on 2017/7/4.
 */
public class MultiThreadLong {

    public static long i = 0;

    /**
     * 更改全局变量i的值
     */
    public static class ChangeT implements Runnable {

        public long to;

        public ChangeT(long to) {
            this.to = to;
        }

        @Override
        public void run() {
            while (true) {
                MultiThreadLong.i = to;
                Thread.yield();
            }
        }
    }

    /**
     * 读取i的值
     */
    public static class ReadT implements Runnable {

        @Override
        public void run() {
            while (true) {
                long tmp = MultiThreadLong.i;
                System.out.println(tmp);
                Thread.yield();
            }
        }
    }

    public static void main(String[] args) {
        new Thread(new ChangeT(111)).start();
        new Thread(new ChangeT(-999)).start();
        new Thread(new ChangeT(333)).start();
        new Thread(new ChangeT(-444)).start();
        new Thread(new ReadT()).start();
    }
}
