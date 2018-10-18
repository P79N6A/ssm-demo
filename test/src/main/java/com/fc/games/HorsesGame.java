package com.fc.games;

/**
 * @author fangcong on 2018/9/29.
 */
public class HorsesGame {

    public static void main(String[] args) {
        int loop = 100;
        int k;
        for (int i = 0; i < loop / 3; i++) {
            for (int j = 0; j < loop / 2; j++) {
                k = 100 - i - j;
                if (k % 2 == 0 && (3 * i + 2 * j + k / 2 == loop)) {
                    System.out.println("[big = " + i + ";media = " + j + ";small = " + k + "]");
                }
            }
        }
    }
}
