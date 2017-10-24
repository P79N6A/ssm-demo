package com.demo.thread;

import java.util.concurrent.CountDownLatch;

/**
 * 结合volatile和synchronize实现线程同步
 * @author fangcong
 *
 */
public class CountImprov2 {

	private volatile static int count = 0;
	private static CountDownLatch countDownLatch = new CountDownLatch(1000);
	
	/**
	 * 定义同步方法实现变量的自增
	 */
	public synchronized static int increment(){
		return count++;
	}
	
	/**
	 * 定义get方法获取变量的值
	 */
	public static int getCount(){
		return count;
	}
	
	public static void main(String[] args) {
		int loop = 1000;
		for(int i = 0;i < loop;i++){
			new Thread(){
				@Override
				public void run() {
					increment();
					countDownLatch.countDown();
				}
			}.start();
		}
		try {
			countDownLatch.await();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		System.out.println("count=" + getCount());
	}
}
