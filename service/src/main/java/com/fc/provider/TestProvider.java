package com.fc.provider;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.annotation.PostConstruct;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * Created by fangcong on 2017/3/31.
 */
public class TestProvider {

    private static final Logger logger = LoggerFactory.getLogger(TestProvider.class);

    private ExecutorService executorService = new ThreadPoolExecutor(4, 4, 0L, TimeUnit.MILLISECONDS,
            new ArrayBlockingQueue<Runnable>(20));

    /**
     * 初始化方法，服务启动是执行
     */
    public void initMethod() {

        executorService.execute(new Runnable() {
            @Override
            public void run() {
                logger.debug("init begin...");
            }
        });

    }

    @PostConstruct
    public void afterInitMentod() {
        logger.debug("after construct...");
    }
}
