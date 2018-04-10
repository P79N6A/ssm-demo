package com.demo.future.parallel;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

/**
 * @author fangcong on 2018/4/8.
 */
public class Div implements Runnable {

    public static BlockingQueue<Msg> bq = new LinkedBlockingQueue<>();

    @Override
    public void run() {
        while (true) {
            try {
                Msg msg = bq.take();
                msg.i = msg.i / 2;
                System.out.println("orgStr = " + msg.i);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
