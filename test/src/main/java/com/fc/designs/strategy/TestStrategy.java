package com.fc.designs.strategy;

/**
 * 策略模式使用场景：
 * #1、一个系统里有许多类，他们的区别仅在于行为不同，可动态让一个对象选择一种行为<br>
 * #2、一个系统需要动态在几种算法中选择一种<br>
 * #3、如果一个对象有很多的行为，如果不用恰当的模式，这些行为就只好使用多重的条件选择语句来实现<br>
 *
 * @author fangcong on 2018/7/30.
 */
public class TestStrategy {

    public static void main(String[] args) {
        Context context = new Context(new AddStrategy());
        System.out.println("10 + 5 = " + context.executeStrategy(10, 5));

        context = new Context(new SubStrategy());
        System.out.println("10 - 5 = " + context.executeStrategy(10, 5));
    }
}
