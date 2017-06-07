package com.fc.designs.observer;

/**
 * @author fangcong on 2017/4/20.
 * 布告板，可扩展
 */
public class CurrentConditionDisplay implements Observer, DisplayElement{

    /**
     * 温度
     */
    private Float temp;
    /**
     * 湿度
     */
    private Float humidity;
    /**
     * 气象数据对象
     */
    private WeatherData weatherData;

    public CurrentConditionDisplay(WeatherData weatherData) {
        this.weatherData = weatherData;
        weatherData.registerObserver(this);
    }

    @Override
    public void display() {
        System.out.println("current temp:" + temp + " humidity:" + humidity);
    }

    @Override
    public void update(float temp, float humidity, float pressure) {
        this.temp = temp;
        this.humidity = humidity;
        display();
    }
}
