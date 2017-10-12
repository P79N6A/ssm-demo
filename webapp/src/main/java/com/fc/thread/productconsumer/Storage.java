package com.fc.thread.productconsumer;

import java.util.LinkedList;

/**
 * Created by fangcong on 2017/4/17.
 * 生产者-消费者模式示例
 */
public class Storage {

    /**
     * 最大容量
     */
    private static final Integer MAX_SIZE = 100;

    /**
     * 仓库存储的载体
     */
    private LinkedList<Object> list = new LinkedList<>();

    /**
     * 生成num个产品
     * @param num
     */
    public void produce(int num) {
        synchronized (list) {
            //仓库容量不足时
            while (list.size() + num > MAX_SIZE) {
                System.out.println("【要生产的产品数量】:" + num + "/t【库存量】:"
                        + list.size() + "/t容量不足暂时不能执行生产任务!");
                try {
                    //不满足生产条件，阻塞
                    list.wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            //容量足够，生产num个产品
            for (int i = 1; i <= num; i++) {
                list.add(new Object());
            }
            System.out.println("【已经生产产品数】:" + num + "/t【现仓储量为】:" + list.size());
            list.notifyAll();
        }
    }

    /**
     * 消费num个产品
     * @param num
     */
    public void consume(int num) {
        synchronized (list) {
            while ( list.size() < num) {
                System.out.println("【要消费的产品数量】:" + num + "/t【库存量】:"
                        + list.size() + "/t产品不足暂时不能执行消费任务!");
                try {
                    // 由于条件不满足，消费阻塞
                    list.wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }

            for (int i = 1; i <= num; i++) {
                list.remove();
            }
            System.out.println("【已经消费产品数】:" + num + "/t【现仓储量为】:" + list.size());
            list.notifyAll();
        }
    }

    public LinkedList<Object> getList() {
        return list;
    }

    public void setList(LinkedList<Object> list) {
        this.list = list;
    }

    public int getMaxSize() {
        return MAX_SIZE;
    }
}
