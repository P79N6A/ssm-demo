package com.demo.future;

/**
 * RealData的虚拟实现(或代理),Future模式的关键
 *
 * @author fangcong on 2018/4/8.
 */
public class FutureData implements Data {

    /**
     * 对realData的封装
     */
    protected RealData realData = null;

    protected boolean isReady = false;

    /**
     * 注入realData，注入成功通知getRealData
     *
     * @param realData
     */
    public synchronized void setRealData(RealData realData) {
        if (isReady) {
            return;
        }
        this.realData = realData;
        isReady = true;
        notifyAll();
    }

    /**
     * 一直等待直到RealData注入成功
     *
     * @return
     */
    @Override
    public synchronized String getResult() {
        while (!isReady) {
            try {
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        return realData.result;
    }
}
