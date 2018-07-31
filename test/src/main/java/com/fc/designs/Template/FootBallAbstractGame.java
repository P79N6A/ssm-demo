package com.fc.designs.template;

/**
 * @author fangcong on 2018/7/31.
 */
public class FootBallAbstractGame extends AbstractGame {

    @Override
    void initilization() {
        System.out.println("football init...");
    }

    @Override
    void startPlay() {
        System.out.println("start football...");
    }

    @Override
    void endPlay() {
        System.out.println("football game over...");
    }
}
