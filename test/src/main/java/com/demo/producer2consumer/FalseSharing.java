package com.demo.producer2consumer;

/**
 * @author fangcong on 2018/4/4.
 */
public final class FalseSharing implements Runnable {

    public static final Integer NUM_THREADS = 4;

    public static final Long ITERATIONS = 500 * 1000 * 1000L;

    private final int arrayIndex;

    private static VolatileLong[] longs = new VolatileLong[NUM_THREADS];

    static {
        for (int i = 0; i < longs.length; i++) {
            longs[i] = new VolatileLong();
        }
    }

    public FalseSharing(final int arrayIndex) {
        this.arrayIndex = arrayIndex;
    }

    private static void runTest() throws InterruptedException {
        Thread[] threads = new Thread[NUM_THREADS];

        for (int i = 0; i < threads.length; i++) {
            threads[i] = new Thread(new FalseSharing(i));
        }

        for (Thread thread : threads) {
            thread.start();
        }

        for (Thread thread : threads) {
            thread.join();
        }
    }

    @Override
    public void run() {
        long i = ITERATIONS + 1;
        while (0 != --i) {
            longs[arrayIndex].value = i;
        }
    }

    public static void main(String[] args) throws Exception {
        final long start = System.currentTimeMillis();
        runTest();
        System.out.println("cost:" + (System.currentTimeMillis() - start));
    }

    /**
     * 解决伪共享问题注解，不需要再声明padding变量
     */
    @sun.misc.Contended
    public static final class VolatileLong {
        public volatile long value = 0;
    }
}


