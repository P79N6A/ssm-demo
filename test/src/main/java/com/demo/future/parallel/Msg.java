package com.demo.future.parallel;

/**
 * 计算(A + B) * C / 2 ，拆分为
 * p1 = A + B
 * p2 = p1 * C
 * p3 = p2/2
 *
 * @author fangcong on 2018/4/8.
 */
public class Msg {

    public double i;

    public double j;

    public String orgStr = null;
}
