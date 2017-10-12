package com.fc.provider;

import java.rmi.Naming;
import java.rmi.registry.LocateRegistry;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;

import com.ali.com.google.common.util.concurrent.ThreadFactoryBuilder;
import com.fc.rmi.UserServiceRMI;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Created by fangcong on 2017/3/31.
 */
public class TestProvider {

    private static final Logger logger = LoggerFactory.getLogger(TestProvider.class);

    /**
     * 创建线程或线程池时需指定有意义的线程名称，方便出错时回溯
     */
    private ExecutorService executorService = new ThreadPoolExecutor(4, 4, 0L, TimeUnit.MILLISECONDS,
            new ArrayBlockingQueue<Runnable>(20), new ThreadFactoryBuilder().setNameFormat("demo-pool-%d").build(),
            new ThreadPoolExecutor.AbortPolicy()
        );

    @Resource
    private UserServiceRMI userServiceRMI;

    /**
     * 初始化方法，服务启动是执行
     */
    public void initMethod() {
        registerRMIService();
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

    /**
     * 服务启动时注册RMI服务
     */
    public void registerRMIService() {
        try {
            //注册通讯接口
            LocateRegistry.createRegistry(6600);
            //注册通讯路径
            Naming.rebind("rmi://127.0.0.1:6600/UserServiceRMI", userServiceRMI);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
