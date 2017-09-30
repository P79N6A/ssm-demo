package test.test;

import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

import com.ali.com.google.common.util.concurrent.ThreadFactoryBuilder;

/**
 * @author fangcong on 2017/9/11.
 */
public class ScheduleTimerDemo {

    public static void main(String[] args) {
        ScheduledExecutorService executorService = new ScheduledThreadPoolExecutor(5,
            new ThreadFactoryBuilder().setNameFormat("demo-pool-%d").build());
        executorService.scheduleAtFixedRate(new Runnable() {

            private volatile int i = 0;

            @Override
            public void run() {
                System.out.println(Thread.currentThread().getName() + i++);
            }
        }, 5, 1, TimeUnit.SECONDS);
    }
}
