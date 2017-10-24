package com.fc.designs.decorator;

/**
 * @author fangcong on 2017/4/20.
 * 抽奖组件
 */
public abstract class AbstractBeverage {

    String description = "Unknown Beverage";

    public String getDescription() {
        return  description;
    }

    /**
     * 花费
     * @return
     */
    public abstract double cost();
}
