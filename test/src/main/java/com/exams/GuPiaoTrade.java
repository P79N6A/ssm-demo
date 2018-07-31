package com.exams;

/**
 * @author fangcong on 2018/2/27.
 */
public class GuPiaoTrade {

    private static final int[] DATA = {25, 10, 22, 5, 75, 65, 80};

    public static void main(String[] args) {
        int p = 0;
        int w1, w2;
        for (int i = 0; i < DATA.length; i++) {
            for (int j = i + 1; j < DATA.length; j++) {
                //第一次交易能产生的利润
                if (DATA[j] > DATA[i]) {
                    w1 = DATA[j] - DATA[i];
                    for (int m = j; m < DATA.length; m++) {
                        for (int n = m + 1; n < DATA.length; n++) {
                            //第二次交易能产生的利润
                            if (DATA[n] > DATA[m]) {
                                w2 = DATA[n] - DATA[m];
                                //预期最大总收益
                                if (w1 + w2 > p) {
                                    p = w1 + w2;
                                    System.out.println(i + " w1 = " + w1 + " w2 = " + w2 + " p = " + p);
                                }
                            }
                        }
                    }
                }
            }
        }
        System.out.println("max = " + p);
    }

}
