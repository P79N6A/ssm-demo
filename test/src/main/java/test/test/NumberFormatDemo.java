package test.test;

import java.text.NumberFormat;

/**
 * @author fangcong on 2018/1/11.
 */
public class NumberFormatDemo {

    public static void main(String[] args) {
        long cost = 3500;
        long total = 9000;
        NumberFormat format = NumberFormat.getPercentInstance();
        format.setMinimumFractionDigits(1);
        String rate = format.format((float)cost/total);
        System.out.println(rate);
    }
}
