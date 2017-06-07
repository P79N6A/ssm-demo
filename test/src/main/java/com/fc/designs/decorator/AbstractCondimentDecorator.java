package com.fc.designs.decorator;

/**
 * @author fangcong on 2017/4/20.
 * 装饰者组件
 */
public abstract class AbstractCondimentDecorator extends AbstractBeverage{

    /**
     * 所有的装饰类都需要重写该方法
     * @return
     */
    @Override
    public abstract String getDescription();
}
