package com.fc.designs.template;

/**
 * @author fangcong on 2018/7/31.
 */
public class TestTemplate {

    public static void main(String[] args) {
        AbstractGame game = new BasketBallAbstractGame();
        game.paly();

        game = new FootBallAbstractGame();
        game.paly();
    }
}
