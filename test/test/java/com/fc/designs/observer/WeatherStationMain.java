package com.fc.designs.observer;

/**
 * @author fangcong on 2017/4/20.
 */
public class WeatherStationMain {

    public static void main(String[] args) {
        WeatherData weatherData = new WeatherData();
        WeatherData weatherData1 = new WeatherData();
        //注册不同观察者
        CurrentConditionDisplay conditionDisplay = new CurrentConditionDisplay(weatherData);
        CurrentConditionDisplay conditionDisplay1 = new CurrentConditionDisplay(weatherData1);

        weatherData.setMeasurements(15f, 20f, 35f);
        weatherData1.setMeasurements(23f, 33f, 34f);
    }
}
