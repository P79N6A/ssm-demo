package com.demo.thread;

import java.util.concurrent.CountDownLatch;

/**
 * volatile关键字使用示例，避免多并发造成的影响
 * @author fangcong
 *
 */
public class CounterImprov1 {

	private static int sum = 0;
	private volatile static int count = 0;
	private static CountDownLatch countDownLatch = new CountDownLatch(100);
	
	public static void main(String[] args) throws Exception {
		for(int i=0;i<2000;i++){
			new Thread(){
				@Override
				public void run() {
					sum = sum + 1;
					count = count + 1;
					countDownLatch.countDown();
				}
			}.start();
		}
		countDownLatch.await();
		System.out.println("sum =" + sum);
		System.out.println("count=" + count);
	}
}
