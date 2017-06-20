package com.fc.designs.decorator;

/**
 * @author fangcong on 2017/4/20.
 */
public class HouseBlend extends AbstractBeverage {

    public HouseBlend() {
        description = "houseBlend coffee";
    }

    @Override
    public double cost() {
        return 2.96;
    }
}
