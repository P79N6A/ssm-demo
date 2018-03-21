package com.demo.thread;

import java.util.ArrayList;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.ForkJoinTask;
import java.util.concurrent.RecursiveTask;

/**
 * Fork/Join模型使用示例：求和
 *
 * @author fangcong on 2018/3/20.
 */
public class ForkJoinDemo extends RecursiveTask<Long> {

    /**
     * 拆分基准值
     */
    private static final int THREHOLD = 10000;

    /**
     * 起始值
     */
    private long start;

    /**
     * 终止值
     */
    private long end;

    public ForkJoinDemo(long start, long end) {
        this.start = start;
        this.end = end;
    }

    @Override
    protected Long compute() {
        long sum = 0;
        boolean canCalc = (end - start) < THREHOLD;
        if (canCalc) {
            for (long i = start; i <= end; i++) {
                sum += i;
            }
        } else {
            long step = (end + start) / 100;
            System.out.println("step : " + step);
            ArrayList<ForkJoinDemo> subTasks = new ArrayList<>();
            long pos = start;
            for (int i = 0; i < 100; i++) {
                long lastOne = pos + step;
                if (lastOne > end) {
                    lastOne = end;
                }
                System.out.println("lastOne" + i + " : " + lastOne);
                ForkJoinDemo task = new ForkJoinDemo(pos, lastOne);
                pos += step + 1;
                subTasks.add(task);
                task.fork();
            }
            for (ForkJoinDemo joinDemo : subTasks) {
                sum += joinDemo.join();
            }
        }
        return sum;
    }

    public static void main(String[] args) {
        ForkJoinPool pool = new ForkJoinPool();
        ForkJoinDemo demo = new ForkJoinDemo(0, 200000);
        ForkJoinTask<Long> result = pool.submit(demo);
        try {
            long res = result.get();
            System.out.println("sum = " + res);
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
    }
}
