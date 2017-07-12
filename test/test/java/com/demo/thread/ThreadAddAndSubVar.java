package com.demo.thread;

/**
 * @author fangcong on 2017/7/4.
 */
public class ThreadAddAndSubVar {

    public static int j = 10;

    public static class AddThread implements Runnable{

        public String name;
        public boolean flag;
        public long time;

        public AddThread(String name, boolean flag, long time) {
            this.name = name;
            this.flag = flag;
            this.time = time;
        }

        public static void addOrSub(boolean flag) {
            if (flag) {
                j += 1;
            } else {
                j -= 1;
            }
        }

        @Override
        public void run() {
            System.out.println(name + " : " + System.currentTimeMillis());
            addOrSub(flag);
            System.out.println(name + " modified value = " + j);
            try {
                Thread.sleep(time);
            }catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public static void main(String[] args) throws InterruptedException{
        Thread a1 = new Thread(new AddThread("a1", true, 5000));
        Thread a2 = new Thread(new AddThread("a2", true, 2000));
        Thread s1 = new Thread(new AddThread("s1", false, 3000));
        Thread s2 = new Thread(new AddThread("s2", false, 4000));
        a1.start();
        a2.start();
        s1.start();
        s2.start();
        a1.join();
        a2.join();
        s1.join();
        s2.join();
        System.out.println("j = " + j);
    }
}
