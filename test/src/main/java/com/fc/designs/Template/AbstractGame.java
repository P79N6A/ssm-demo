package com.fc.designs.template;

/**
 * 模板模式——模板方法设置为final
 *
 * @author fangcong on 2018/7/31.
 */
public abstract class AbstractGame {

    /**
     * 初始化
     */
    abstract void initilization();

    /**
     * 开始游戏
     */
    abstract void startPlay();

    /**
     * 结束游戏
     */
    abstract void endPlay();

    public final void paly() {
        initilization();

        startPlay();

        endPlay();
    }
}
