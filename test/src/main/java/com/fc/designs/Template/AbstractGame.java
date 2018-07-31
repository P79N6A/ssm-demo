package com.fc.designs.template;

/**
 * 模板模式——模板方法设置为final
 *
 * @author fangcong on 2018/7/31.
 */
public abstract class AbstractGame {

    abstract void initilization();

    abstract void startPlay();

    abstract void endPlay();

    public final void paly() {
        initilization();

        startPlay();

        endPlay();
    }
}
