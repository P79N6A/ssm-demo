package com.demo.thread;

import java.util.ArrayList;
import java.util.Vector;

/**
 * @author fangcong on 2017/7/5.
 * ArrayList并发下线程不安全，Vector线程安全
 */
public class ArrayListMultiThread {

    static ArrayList<Integer> arrayList = new ArrayList<>(10);

    static Vector<Integer> vector = new Vector<>(10);

    public static class AddThread implements Runnable {

        @Override
        public void run() {
            int loop = 100000;
            for (int i = 0; i < loop; i++) {
                vector.add(i);
                //arrayList.add(i);
            }
        }
    }

    public static void main(String[] args) throws InterruptedException{
        Thread t1 = new Thread(new AddThread());
        Thread t2 = new Thread(new AddThread());
        t1.start();
        t2.start();
        t1.join();
        t2.join();
        System.out.println(vector.size());
    }
}
