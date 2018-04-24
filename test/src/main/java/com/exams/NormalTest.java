package com.exams;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicLong;
import java.util.concurrent.atomic.LongAdder;

/**
 * @author fangcong on 2018/4/10.
 */
public class NormalTest {

    private static final int MAX_THREAD = 3;

    private static final int TASK_COUNT = 2;

    private static final int TASK_TARGET = 10000000;

    /**
     * 无锁的原子操作
     */
    private AtomicLong acount = new AtomicLong(0);

    /**
     * 更快的原子类
     */
    private LongAdder lacount = new LongAdder();

    /**
     * 非原子操作
     */
    private long count = 0;

    static CountDownLatch cdlSync = new CountDownLatch(TASK_COUNT);

    static CountDownLatch cdlAtomic = new CountDownLatch(TASK_COUNT);

    static CountDownLatch cdlAdder = new CountDownLatch(TASK_COUNT);

    protected synchronized long incr() {
        return ++count;
    }

    protected synchronized long getCount() {
        return count;
    }

    public class SyncThread implements Runnable {

        private String name;

        private long startTime;

        NormalTest normalTest;

        public SyncThread(long startTime, NormalTest normalTest) {
            this.startTime = startTime;
            this.normalTest = normalTest;
        }

        @Override
        public void run() {
            long v = normalTest.getCount();
            while (v < TASK_TARGET) {
                v = normalTest.incr();
            }
            long endTime = System.currentTimeMillis();
            System.out.println("long cost:" + (endTime -startTime) + "ms v:" + v);
            cdlSync.countDown();
        }
    }

    public class AtomicThread implements Runnable {

        protected String name;

        protected long startTime;

        public AtomicThread(long startTime) {
            this.startTime = startTime;
        }

        @Override
        public void run() {
            long v = acount.get();
            while (v < TASK_TARGET) {
                v = acount.incrementAndGet();
            }
            long endTime = System.currentTimeMillis();
            System.out.println("AtomicLong cost:" + (endTime -startTime) + "ms v:" + v);
            cdlAtomic.countDown();
        }
    }

    public class AdderThread implements Runnable {

        protected long startTime;

        public AdderThread(long startTime) {
            this.startTime = startTime;
        }

        @Override
        public void run() {
            long v = lacount.sum();
            while (v < TASK_TARGET) {
                lacount.increment();
                v= lacount.sum();
            }
            long endTime = System.currentTimeMillis();
            System.out.println("AdderLong cost:" + (endTime -startTime) + "ms v:" + v);
            cdlAdder.countDown();
        }
    }

    public void testSync() throws InterruptedException {
        ExecutorService service = Executors.newFixedThreadPool(MAX_THREAD);
        long startTime = System.currentTimeMillis();
        SyncThread syncThread = new SyncThread(startTime, this);
        for (int i = 0; i < TASK_COUNT; i++) {
            service.submit(syncThread);
        }
        cdlSync.wait();
        service.shutdown();
    }

    public void testAtomic() throws InterruptedException {
        ExecutorService service = Executors.newFixedThreadPool(MAX_THREAD);
        long startTime = System.currentTimeMillis();
        AtomicThread atomicThread = new AtomicThread(startTime);
        for (int i = 0; i < MAX_THREAD; i++) {
            service.submit(atomicThread);
        }
        cdlAtomic.wait();
        service.shutdown();
    }

    public void testAdder() throws InterruptedException {
        ExecutorService service = Executors.newFixedThreadPool(MAX_THREAD);
        long startTime = System.currentTimeMillis();
        AdderThread adderThread = new AdderThread(startTime);
        for (int i = 0; i < MAX_THREAD; i++) {
            service.submit(adderThread);
        }
        cdlAdder.wait();
        service.shutdown();
    }

    public static void main(String[] args) throws Exception {
        /*long start = System.nanoTime();
        List<User> list = new ArrayList<>();
        for (int i = 0; i < 100; i++) {
            User user = new User("name" + i, i % 2 == 0 ? 'M' : 'F', 20 + i);
            list.add(user);
        }
        List<User> subList = list.subList(0, 10);*/
        /*List<User> subList = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            subList.add(list.get(i));
        }*/
        //System.out.println("cost:" + (System.nanoTime() - start));

        /*final int num = 2;
        Function<Integer, Integer> function = (from) -> from * num;
        System.out.println(function.apply(3));

        List<Double> nums = Arrays.asList(12d, 14d, 17d, 22d);
        nums.stream().map((d) -> d.toString()).forEach(System.out::print);*/

        //NormalTest normalTest = new NormalTest();
        //normalTest.testSync();
        //normalTest.testAtomic();
        //normalTest.testAdder();

        int a = 132547698;
        char[] c = String.valueOf(a).toCharArray();
        System.out.println(niXuStr(c));
    }

    private static String niXuStr(char[] c) {
        if (c.length == 0) {
            return "";
        } else {
            String s1 = String.valueOf(c[c.length - 1]);
            String c2 = String.copyValueOf(c, 0, c.length - 1);
            return s1 + niXuStr(c2.toCharArray());
        }
    }
}


