package com.fc.designs.observer;

import java.util.ArrayList;

/**
 * @author fangcong on 2017/4/20.
 * 气象数据中心
 */
public class WeatherData implements Subject {

    /**
     * 观察者列表
     */
    private ArrayList<Observer> observers;
    /**
     * 气温
     */
    private Float temp;
    /**
     * 湿度
     */
    private Float humidity;
    /**
     * 气压
     */
    private Float pressure;

    public WeatherData() {
        observers = new ArrayList();
    }

    @Override
    public void registerObserver(Observer observer) {
        observers.add(observer);
    }

    @Override
    public void removeObserver(Observer observer) {
        int i = observers.indexOf(observer);
        if (i > 0) {
            observers.remove(i);
        }
    }

    @Override
    public void notifyAllObserver() {
        for (int i = 0; i < observers.size(); i++) {
            Observer observer = observers.get(i);
            observer.update(temp, humidity, pressure);
        }
    }

    /**
     * 天气改变时通知到观察者
     */
    public void measurementChanged() {
        notifyAllObserver();
    }

    /**
     * 更新最新气象信息
     * @param temp
     * @param humidity
     * @param pressure
     */
    public void setMeasurements(Float temp, Float humidity, Float pressure) {
        this.temp = temp;
        this.humidity = humidity;
        this.pressure = pressure;
        measurementChanged();
    }
}
