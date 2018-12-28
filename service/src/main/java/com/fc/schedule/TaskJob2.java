package com.fc.schedule;

import java.util.concurrent.atomic.AtomicInteger;

import com.fc.common.DateUtils;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

/**
 * @author fangcong on 2018/11/22.
 */
@Component
public class TaskJob2 {

    private AtomicInteger sum = new AtomicInteger(0);

    /**
     * Scheduled注解有3个参数，三个参数同时只允许设置一个，值含义如下：<br>
     *     1、cron:定义表达式，空格分隔，至少包含6个值，表示ss:mm:HH dd MM Month Year <br>
     *     2、fixedDelay:当前任务完成之后间隔多少毫秒执行下一个任务<br>
     *     3、fixedRate:当前任务开始之后间隔多少毫秒执行下一个任务<br>
     */
    @Scheduled(fixedDelay = 150000)
    public void run() {
        int c = sum.incrementAndGet();
        if (c > 10) {
            sum = new AtomicInteger(1);
        }
        try {
            Thread.sleep(1000 * c);
            System.out.println("schedule task job2 run at " + DateUtils.getCurrDateTimeStr());
        } catch (InterruptedException e) {
            System.err.println("task job2 interrupt exception : " + e.getMessage());
        }
    }

    @Scheduled(cron = "0 0 1 * * ?")
    public void run2() {
        System.out.println("schedule task job3 run at " + DateUtils.getCurrDateTimeStr());
    }
}
