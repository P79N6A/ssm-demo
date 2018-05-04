package com.demo.locks;

/**
 * @author fangcong on 2018/5/3.
 */
public class ThreadInterruptDemo {

    public static class TaskThread implements Runnable {

        @Override
        public void run() {
            while (true) {
                if (Thread.currentThread().isInterrupted()) {
                    System.out.println("interrupt...");
                    break;
                }
                System.out.println("alive...");
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public static void main(String[] args) throws Exception{
        Thread t = new Thread(new TaskThread());
        t.start();
        Thread.sleep(2000);
        t.interrupt();
    }
}
