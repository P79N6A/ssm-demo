package com.fc.designs.decorator;

/**
 * @author fangcong on 2017/4/20.
 */
public class Mocca extends AbstractCondimentDecorator {

    AbstractBeverage beverage;

    public Mocca(AbstractBeverage beverage) {
        this.beverage = beverage;
    }

    @Override
    public String getDescription() {
        return beverage.getDescription() + ", mocca";
    }

    @Override
    public double cost() {
        return beverage.cost() + 1.2;
    }
}
