package com.demo.thread;

import java.util.ArrayList;
import java.util.Vector;

/**
 * @author fangcong on 2017/7/5.
 * ArrayList并发下线程不安全，Vector线程安全
 */
public class ArrayListMultiThread {

    private static final int LOOP = 10000;

    static ArrayList<Integer> arrayList = new ArrayList<>(LOOP);

    static Vector<Integer> vector = new Vector<>(LOOP);

    /**
     * 线程安全，可以得到预期结果
     */
    public static class VectorAddThread implements Runnable {

        @Override
        public void run() {
            for (int i = 0; i < LOOP; i++) {
                vector.add(i);
            }
        }
    }

    /**
     * 非线程安全，可能会抛出数据越界异常
     */
    public static class ArrayListAddThread implements Runnable {

        @Override
        public void run() {
            for (int i = 0; i < LOOP; i++) {
                arrayList.add(i);
            }
        }
    }

    public static void main(String[] args) throws InterruptedException{
        VectorAddThread vectorAddThread = new VectorAddThread();
        Thread t1 = new Thread(vectorAddThread);
        Thread t2 = new Thread(vectorAddThread);
        t1.start();
        t2.start();
        t1.join();
        t2.join();
        System.out.println(vector.size());
        ArrayListAddThread arrayListAddThread = new ArrayListAddThread();
        Thread t4 = new Thread(arrayListAddThread);
        Thread t5 = new Thread(arrayListAddThread);
        t4.start();
        t5.start();
        t4.join();
        t5.join();
        System.out.println(arrayList.size());

        ArrayList<Integer> arrayList1 = new ArrayList<>(10);
        for (int i = 0; i < 20; i++) {
            arrayList1.add(i);
        }
        System.out.println(arrayList1.size());
    }
}
