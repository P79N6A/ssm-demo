package com.fc.thread.productconsumer;

/**
 * @author fangcong on 2017/4/17.
 *         生产者线程
 */
public class Producer extends Thread {

    private Integer num;

    private Storage storage;

    public Producer(Storage storage) {
        this.storage = storage;
    }

    @Override
    public void run() {
        produce(num);
    }

    /**
     * 调用仓库Storage的生产函数
     *
     * @param num
     */
    public void produce(int num) {
        storage.produce(num);
    }

    public Storage getStorage() {
        return storage;
    }

    public void setStorage(Storage storage) {
        this.storage = storage;
    }

    public Integer getNum() {
        return num;
    }

    public void setNum(Integer num) {
        this.num = num;
    }
}
