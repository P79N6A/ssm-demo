package com.fc.thread;

/**
 * 守护线程
 *
 * @author fangcong on 2017/12/20.
 */
public class DaemonThreadDemo {

    public static class TestRunnable implements Runnable {

        @Override
        public void run() {
            try {
                //让守护线程阻塞1s后执行,实际上下面不会有下面的输出，因为阻塞时守护线程已退出
                Thread.sleep(1000);
                System.out.println("daemon is print message ...");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * 用户线程
     */
    public static class UserThread implements Runnable {

        @Override
        public void run() {
            for (int i = 1; i <= 5; i++) {
                System.out.println("线程第" + i + "次执行");
                try {
                    Thread.sleep(2000);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
    }

    /**
     * 守护线程
     */
    public static class DaemonThread implements Runnable {

        @Override
        public void run() {
            for (int i = 1; i <= 10; i++) {
                System.out.println("守护线程第" + i + "次执行");
                try {
                    Thread.sleep(2000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public static void main(String[] args) {
        //1、设置守护线程要在线程start之前
        Thread thread = new Thread();
        thread.setName("daemon-1");
        thread.setDaemon(true);
        thread.start();
        System.out.println(thread.getName() + " is daemon : " + thread.isDaemon());

        //2、所有用户线程退出后守护线程也会退出，因此不是所有应该都适合分配守护线程进行服务
        Thread thread1 = new Thread(new TestRunnable());
        thread1.setDaemon(true);
        thread1.start();
        System.out.println(thread1.isAlive());

        //3、批量任务守护线程的执行
        Thread thread2 = new Thread(new UserThread());
        Thread thread3 = new Thread(new DaemonThread());
        thread3.setDaemon(true);
        thread2.start();
        thread3.start();
    }
}
