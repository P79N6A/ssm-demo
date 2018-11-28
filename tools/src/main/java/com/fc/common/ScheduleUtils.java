package com.fc.common;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ScheduledFuture;

import org.apache.log4j.Logger;
import org.springframework.scheduling.concurrent.ThreadPoolTaskScheduler;

/**
 * 定时任务工具类
 *
 * @author fangcong on 2018/11/23.
 */
public class ScheduleUtils {

    private static final Logger LOGGER = Logger.getLogger(ScheduleUtils.class);

    /**
     * TaskSchedule实现定时任务的添加、修改、删除等动态操作
     */
    private static ThreadPoolTaskScheduler scheduler = new ThreadPoolTaskScheduler();

    private static Map<String, ScheduledFuture<?>> scheduledFutureMap = new HashMap<>();

    static {
        scheduler.initialize();
        System.out.println("定时任务线程池启动");
        LOGGER.info("定时任务线程池启动");
    }

    /**
     * 定时任务启动
     *
     * @param startTime 启动时间,还可以设置fixedDelay或fixedRate
     */
    public static void start(ScheduleTask task, Date startTime) {
        ScheduledFuture scheduledFuture = scheduler.schedule(task, startTime);
        scheduledFutureMap.put(task.getId(), scheduledFuture);
        System.out.println("定时任务" + task.getId() + "启动");
        LOGGER.info("定时任务" + task.getId() + "启动");
    }

    /**
     * 取消任务
     *
     * @param task
     */
    public static void cancal(ScheduleTask task) {
        ScheduledFuture scheduledFuture = scheduledFutureMap.get(task.getId());
        if (null != scheduledFuture && !scheduledFuture.isCancelled()) {
            scheduledFuture.cancel(false);
        }
        scheduledFutureMap.remove(task.getId());
        System.out.println("任务" + task.getId() + "已取消");
        LOGGER.info("任务" + task.getId() + "已取消");
    }

    /**
     * 修改定时任务
     *
     * @param task
     * @param startTime
     */
    public static ScheduleTask modify(ScheduleTask task, Date startTime) {
        cancal(task);
        task.setId(task.getId() + "new");
        start(task, startTime);
        return task;
    }

    public static class ScheduleTask implements Runnable {

        private static final Logger LOGGER = Logger.getLogger(ScheduleTask.class);

        private String id;

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public ScheduleTask(String id) {
            this.id = id;
        }

        @Override
        public void run() {
            LOGGER.info(id + " begin run at " + DateUtils.getCurrDateTimeStr());
        }
    }

    public static void main(String[] args) throws InterruptedException {
        ScheduleTask scheduleTask = new ScheduleTask("task1");
        ScheduleUtils.start(scheduleTask, new Date());

        Thread.sleep(1000);
        ScheduleTask task = ScheduleUtils.modify(scheduleTask, DateUtils.getFormatDateTime("2018-11-23 11:13:00"));

        Thread.sleep(1000);
        ScheduleUtils.cancal(task);
    }
}
