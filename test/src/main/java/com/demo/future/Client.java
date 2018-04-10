package com.demo.future;

/**
 * 客户端程序，构造FutureData，开启构造RealData的线程，并返回futureData
 *
 * @author fangcong on 2018/4/8.
 */
public class Client {

    /**
     * 不会等待realData真正构造完成
     *
     * @param queryStr
     * @return
     */
    public Data request(final String queryStr) {
        final FutureData futureData = new FutureData();
        new Thread(() -> {
            RealData realData = new RealData(queryStr);
            futureData.setRealData(realData);
        }).start();
        return futureData;
    }
}
