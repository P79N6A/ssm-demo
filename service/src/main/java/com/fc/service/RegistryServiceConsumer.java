package com.fc.service;

/**
 * @author fangcong on 2017/5/2.
 */
public interface RegistryServiceConsumer {

    /**
     * dubbo消费者接口方法
     *
     * @param words
     * @return
     */
    String sayHello(String words);
}
