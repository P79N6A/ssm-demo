package com.demo.locks;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 公平锁与非公平锁测试
 *
 * @author fangcong on 2018/4/24.
 */
public class FairAndNonfairTest {

    /**
     * 主要是为了透出阻塞线程队列
     */
    private static class ReentrantLock2 extends ReentrantLock {

        public ReentrantLock2(boolean fair) {
            super(fair);
        }

        @Override
        public Collection<Thread> getQueuedThreads() {
            List<Thread> list = new ArrayList<>(super.getQueuedThreads());
            Collections.reverse(list);
            return list;
        }
    }

    private static Lock fairLock = new ReentrantLock2(true);
    private static Lock unFairLock = new ReentrantLock2(false);

    private static class Job extends Thread {
        private Lock lock;
        public Job(Lock lock, String name) {
            this.lock = lock;
            this.setName(name);
        }

        @Override
        public void run() {
            lock.lock();
            try {
                System.out.println("current thread:" + Thread.currentThread().getName());
                ReentrantLock2 lock2 = (ReentrantLock2)lock;
                System.out.println(lock2.getQueuedThreads().toString());
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                lock.unlock();
            }
        }
    }

    private static void testLock(Lock lock) throws InterruptedException {
        for (int i = 0; i < 5; i++) {
            Job job = new Job(lock, "job" + i);
            job.start();
            job.join();
        }
    }

    public static void main(String[] args) throws InterruptedException {
        testLock(fairLock);
        Thread.sleep(2000);
        System.out.println("===========");
        testLock(unFairLock);
    }
}
