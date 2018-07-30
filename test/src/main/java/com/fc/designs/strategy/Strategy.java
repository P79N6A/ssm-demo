package com.fc.designs.strategy;

/**
 * 策略模式——根据不同行为选择不同策略，定义默认行为接口
 *
 * @author fangcong on 2018/7/30.
 */
public interface Strategy {

    public int doOperation(int num1, int num2);
}
