package com.demo.thread;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.AbstractQueuedSynchronizer;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;

/**
 * 自定义同步组件，同一时刻只允许一个线程占有锁
 *
 * @author fc on 2018/4/24.
 */
public class MutexAQS implements Lock {

    /**
     * 静态内部类，继承同步器并实现独占式获取和释放同步状态
     */
    private static class Sync extends AbstractQueuedSynchronizer {
        /**
         * 是否处于占用状态:1-占用;0-未占用
         *
         * @return
         */
        @Override
        protected boolean isHeldExclusively() {
            return getState() == 1;
        }

        /**
         * 当状态为0时获取锁
         *
         * @param acquires
         * @return
         */
        @Override
        public boolean tryAcquire(int acquires) {
            if (compareAndSetState(0, 1)) {
                setExclusiveOwnerThread(Thread.currentThread());
                return true;
            }
            return false;
        }

        /**
         * 释放锁并将状态置为0
         *
         * @param releases
         * @return
         */
        @Override
        protected boolean tryRelease(int releases) {
            if (getState() == 0) {
                throw new IllegalMonitorStateException();
            }
            setExclusiveOwnerThread(null);
            setState(0);
            return true;
        }

        /**
         * 返回一个condition
         *
         * @return
         */
        Condition newCondition() {
            return new ConditionObject();
        }
    }

    /**
     * 将操作代理到sync
     */
    private static final Sync SYNC = new Sync();

    @Override
    public void lock() {
        SYNC.acquire(1);
    }

    @Override
    public void lockInterruptibly() throws InterruptedException {
        SYNC.acquireInterruptibly(1);
    }

    @Override
    public boolean tryLock() {
        return SYNC.tryAcquire(1);
    }

    @Override
    public boolean tryLock(long time, TimeUnit unit) throws InterruptedException {
        return SYNC.tryAcquireNanos(1, unit.toNanos(time));
    }

    @Override
    public void unlock() {
        SYNC.release(1);
    }

    @Override
    public Condition newCondition() {
        return SYNC.newCondition();
    }

    public boolean isLocked() {
        return SYNC.isHeldExclusively();
    }

    public boolean hasQueuedThreads() {
        return SYNC.hasQueuedThreads();
    }
}