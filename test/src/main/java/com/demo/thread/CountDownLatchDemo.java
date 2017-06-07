package com.demo.thread;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.CountDownLatch;

/**
 * CountDownLatch 同步辅助类的常用方法示例<br>
 * CountDownLatch(int count);构造方法参数指定了计数的次数<br>
 * public void countDown();当前线程调用此方法，则计数减一<br>
 * public void await() 调用此方法会一直阻塞当前线程，直到计时器的值为0<br>
 * @author fangcong
 *
 */
public class CountDownLatchDemo {

	private final static SimpleDateFormat format = new SimpleDateFormat("yy-MM-dd HH:mm:ss");
	
	public static void main(String[] args) throws Exception {
		CountDownLatch countDownLacth = new CountDownLatch(2);
		Worker work1 = new Worker("zhangsan", 5000, countDownLacth);
		Worker work2 = new Worker("lisi", 8000, countDownLacth);
		work1.start();
		work2.start();
		//等待两人工作完成
		countDownLacth.await();
		System.out.println("均完成后的时间：" + format.format(new Date()));
	}
	
	static class Worker extends Thread{
		private String workName;
		private int workTime;
		private CountDownLatch latch;
		
		public Worker(String workName, int workTime, CountDownLatch latch) {
			this.workName = workName;
			this.workTime = workTime;
			this.latch = latch;
		}
		
		@Override
		public void run(){
			System.out.println("Worker "+workName+" do work begin at "+format.format(new Date()));  
            doWork();//工作了  
            System.out.println("Worker "+workName+" do work complete at "+format.format(new Date()));  
            latch.countDown();//工人完成工作，计数器减一 
		}
		
		private void doWork(){
			try {  
                Thread.sleep(workTime);  
            } catch (InterruptedException e) {  
                e.printStackTrace();  
            }
		}
	}
}
