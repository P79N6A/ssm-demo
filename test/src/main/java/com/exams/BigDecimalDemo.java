package com.exams;

import java.math.BigDecimal;
import java.math.MathContext;
import java.util.Random;

/**
 * 测试BigDecimal运算时精度问题
 *
 * @author fangcong on 2018/9/12.
 */
public class BigDecimalDemo {

    /**
     * 加减乘除运算
     */
    private static final char[] CALC_SYMS = {'+', '-', '*', '/'};

    /**
     * int类型运算
     *
     * @param a
     * @param b
     * @param sym 运算符号
     * @return
     */
    public static BigDecimal calcInteger(BigDecimal d1, BigDecimal d2, char sym) {
        BigDecimal result = new BigDecimal(0);
        switch (sym) {
            case '+' :
                result = d1.add(d2);
                break;
            case '-' :
                result = d1.subtract(d2);
                break;
            case '*' :
                result = d1.multiply(d2);
                break;
            case '/' :
                result = d1.divide(d2, new MathContext(5));
                break;
            default :
                break;
        }
        return result;
    }

    public static void main(String[] args) throws Exception {
        /*BigDecimal b1 = new BigDecimal(15);
        BigDecimal b2 = new BigDecimal(5);
        BigDecimal r1 = calcInteger(b1, b2, CALC_SYMS[3]);
        System.out.println(r1.toString());*/
        for (int i = 0; i < 200; i++) {
            Random random = new Random();
            int rate = random.nextInt(1000);
            System.out.print(rate + "\t");
            if (i % 20 == 0) {
                System.out.println();
                Thread.sleep(1000);
            }
        }
    }
}
