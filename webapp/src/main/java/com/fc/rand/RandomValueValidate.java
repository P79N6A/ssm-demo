package com.fc.rand;

import java.util.Random;

import com.fc.constant.NormalNumberConstant;

/**
 * @author fangcong on 2017/6/26.
 */
public class RandomValueValidate {

    private static final Random RANDOM1 = new Random(1);
    private static final Random RANDOM2 = new Random(1);

    public static void main(String[] args) {
        int count = 0;
        for (int i = 0; i < NormalNumberConstant.INT_100; i++) {
            int a1 = RANDOM1.nextInt(100);
            int a2 = RANDOM2.nextInt(100);
            if (a1 == a2) {
                if (i % 10 == 0) {
                    System.out.println();
                }
                System.out.print(a1 + "\t");
                count++;
            } else {
                if (i % 10 == 0) {
                    System.out.println();
                }
                System.out.print(a1 + "--" + a2 + "\t");
            }
        }
        System.out.println();
        System.out.println("count ==" + count);
    }
}
