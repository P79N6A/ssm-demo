package com.fc.designs.decorator;

/**
 * @author fangcong on 2017/4/20.
 */
public class Espresso extends AbstractBeverage {

    public Espresso() {
        description = "espresso";
    }

    @Override
    public double cost() {
        return 1.99;
    }
}
