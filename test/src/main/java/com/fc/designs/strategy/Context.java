package com.fc.designs.strategy;

/**
 * 使用某种策略的类，可以随意改变策略行为
 *
 * @author fangcong on 2018/7/30.
 */
public class Context {

    private Strategy strategy;

    public Context(Strategy strategy) {
        this.strategy = strategy;
    }

    public int executeStrategy(int num1, int num2) {
        return strategy.doOperation(num1, num2);
    }
}
