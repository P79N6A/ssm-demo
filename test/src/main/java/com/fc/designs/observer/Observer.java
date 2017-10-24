package com.fc.designs.observer;

/**
 * @author fangcong
 * 定义观察者接口
 */
public interface Observer {

    /**
     * 更新气象板数据
     * @param temp      温度
     * @param humidity  湿度
     * @param pressure  气压
     */
    void update(float temp, float humidity, float pressure);
}
