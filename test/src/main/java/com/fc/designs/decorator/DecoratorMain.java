package com.fc.designs.decorator;

/**
 * @author fangcong on 2017/4/20.
 */
public class DecoratorMain {

    public static void main(String[] args) {
        AbstractBeverage beverage = new Espresso();
        System.out.println(beverage.getDescription() + " cost : " + beverage.cost());

        AbstractBeverage beverage1 = new HouseBlend();
        beverage1 = new Mocca(beverage1);
        System.out.println(beverage1.getDescription() + " cost : " + beverage1.cost());
        beverage = new Mocca(beverage);
        System.out.println(beverage.getDescription() + " cost : " + beverage.cost());
    }
}
