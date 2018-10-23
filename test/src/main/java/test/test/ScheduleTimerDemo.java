package test.test;

import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

import com.google.common.util.concurrent.ThreadFactoryBuilder;

/**
 * @author fangcong on 2017/9/11.
 * 定时任务处理,替代Timer,Timer运行多个定时任务时，只要其中之一没有捕获抛出的异常
 * 其它任务便会自动终止
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
