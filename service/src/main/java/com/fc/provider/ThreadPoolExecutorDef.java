package com.fc.provider;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.CancellationException;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;
import java.util.concurrent.RejectedExecutionHandler;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * 自定义线程池
 *
 * @author fangcong on 2018/4/27.
 */
public class ThreadPoolExecutorDef extends ThreadPoolExecutor {

    /**
     * 重写构造方法
     *
     * @param corePoolSize    核心线程数量
     * @param maximumPoolSize 最大线程数量
     * @param keepAliveTime   非核心线程闲置存活时间
     * @param unit            时间单位
     * @param workQueue       工作队列
     * @param threadFactory   线程工厂
     * @param handler         拒绝策略
     */
    public ThreadPoolExecutorDef(int corePoolSize, int maximumPoolSize, long keepAliveTime,
                                 TimeUnit unit,
                                 BlockingQueue<Runnable> workQueue,
                                 ThreadFactory threadFactory,
                                 RejectedExecutionHandler handler) {
        super(corePoolSize, maximumPoolSize, keepAliveTime, unit, workQueue, threadFactory, handler);
    }

    @Override
    protected void beforeExecute(Thread t, Runnable r) {
        super.beforeExecute(t, r);
        System.out.println("run task thread : " + t.getName());
        System.out.println("the task : " + r.toString());
    }

    @Override
    protected void afterExecute(Runnable r, Throwable t) {
        super.afterExecute(r, t);
        if (t == null && r instanceof Future<?>) {
            try {
                Object result = ((Future<?>)r).get();
                System.out.println("callback:" + result.toString());
            } catch (CancellationException ce) {
                t = ce;
            } catch (ExecutionException ee) {
                t = ee.getCause();
            } catch (InterruptedException ie) {
                Thread.currentThread().interrupt();
            }
        }
        if (t != null) {
            System.out.println(t);
        }
    }
}
