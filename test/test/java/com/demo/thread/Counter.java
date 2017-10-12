package com.demo.thread;

/**
 * volatile关键字的使用示例
 * @author fangcong
 *
 */
public class Counter {

	/**
	 * 定义变量，用关键字和不用关键字volatile
	 */
	private static int i = 0;
	private volatile static int j = 0;
	
	public static void inc(){
		//延迟1s使结果明显
		try {
			Thread.sleep(1);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		i++;
		j++;
	}
	
	public static void main(String[] args) {
		int count = 100;
		//同时启动100个线程，查看结果
		for (int k = 0; k < count; k++) {
			new Thread(new Runnable() {
				
				@Override
				public void run() {
					inc();
				}
			}).start();
		}
		System.out.println("i=" + i);
		System.out.println("j=" + j);
	}
}
