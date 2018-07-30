package com.fc.designs.strategy;

/**
 * @author fangcong on 2018/7/30.
 */
public class SubStrategy implements Strategy {

    @Override
    public int doOperation(int num1, int num2) {
        return num1 > num2 ? num1 - num2 : num2 - num1;
    }
}
