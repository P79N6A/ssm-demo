package com.fc.thread.productAndConsumer;

/**
 * Created by fangcong on 2017/4/17.
 * 消费者线程
 */
public class Consumer extends Thread {

    private Integer num;

    private Storage storage;

    public Consumer(Storage storage) {
        this.storage = storage;
    }

    @Override
    public void run() {
        consume(num);
    }

    public void consume(int num) {
        storage.consume(num);
    }

    public Integer getNum() {
        return num;
    }

    public void setNum(Integer num) {
        this.num = num;
    }

    public Storage getStorage() {
        return storage;
    }

    public void setStorage(Storage storage) {
        this.storage = storage;
    }
}
