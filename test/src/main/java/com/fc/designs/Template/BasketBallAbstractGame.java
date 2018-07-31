package com.fc.designs.template;

/**
 * @author fangcong on 2018/7/31.
 */
public class BasketBallAbstractGame extends AbstractGame {

    @Override
    void initilization() {
        System.out.println("basketball init...");
    }

    @Override
    void startPlay() {
        System.out.println("basketball begin...");
    }

    @Override
    void endPlay() {
        System.out.println("basketball end...");
    }
}
