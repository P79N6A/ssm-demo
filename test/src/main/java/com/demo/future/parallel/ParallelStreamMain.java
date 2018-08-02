package com.demo.future.parallel;

/**
 * 并行流处理
 *
 * @author fangcong on 2018/4/8.
 */
public class ParallelStreamMain {

    public static void main(String[] args) {
        new Thread(new Plus()).start();
        new Thread(new Multiply()).start();
        new Thread(new Div()).start();

        for (int i = 1; i <= 100; i++) {
            for (int j = 1; j <= 100; j++) {
                Msg msg = new Msg();
                msg.i = i;
                msg.j = j;
                Plus.bq.add(msg);
            }
        }
    }
}
