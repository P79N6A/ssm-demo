package com.fc.designs.observer;

/**
 * Created by fangcong on 2017/4/20.
 * @author fangcong
 * 定义观察者模式主题接口
 */
public interface Subject {

    /**
     * 注册一个观察者
     * @param observer
     */
    void registerObserver(Observer observer);

    /**
     * 移除一个观察者
     * @param observer
     */
    void removeObserver(Observer observer);

    /**
     * 通知所有观察者
     */
    void notifyAllObserver();
}
