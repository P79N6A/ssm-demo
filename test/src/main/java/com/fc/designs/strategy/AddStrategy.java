package com.fc.designs.strategy;

/**
 * 策略1：实现增加操作
 *
 * @author fangcong on 2018/7/30.
 */
public class AddStrategy implements Strategy {

    @Override
    public int doOperation(int num1, int num2) {
        return num1 + num2;
    }
}
