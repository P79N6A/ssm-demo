package com.fc.designs.proxy;

/**
 * 代理模式:一个类代表另一个类的功能
 *
 * 和适配器模式区别:代理模式不能改变所代理类的接口
 *
 * 和装饰者模式区别:代理模式是为了加以控制而不是增加功能
 *
 * @author fangcong on 2018/4/10.
 */
public interface Work {

    /**
     * 要代理的方法
     */
    void doWork();
}
