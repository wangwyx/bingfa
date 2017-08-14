package com.wyx.ThreadPool;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

public class UseThreadPoolExecutor2 implements Runnable{

	private static AtomicInteger count = new AtomicInteger(0);
	
	@Override
	public void run() {
		try {
			int temp = count.incrementAndGet();
			System.out.println("任务" + temp);
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) throws Exception{
		/**
		 * 无界队列，除非系统资源耗尽，否则无界d的任务队列不存在任务入队失败的情况。当有新任务到来，系统的线程数小于corepoolsize，则新建线程执行任务，
		 * 当达到corepoolsize后，不再继续增加。若后续仍有新的任务加入，而没有空闲的线程资源，则任务直接进入队列等待。若任务的创建和处理差别很大，
		 * 无界队列会保持快速增长，知道耗尽系统内存
		 */
		//System.out.println(Runtime.getRuntime().availableProcessors());
		BlockingQueue<Runnable> queue = 
				//new LinkedBlockingQueue<Runnable>();
				new ArrayBlockingQueue<Runnable>(10);
		//不同队列，不通方式
		ExecutorService executor  = new ThreadPoolExecutor(
					5, 		//core
					10, 	//max
					120L, 	//2fenzhong
					TimeUnit.SECONDS,
					queue);
		
		for(int i = 0 ; i < 20; i++){
			executor.execute(new UseThreadPoolExecutor2());
		}
		Thread.sleep(1000);
		System.out.println("queue size:" + queue.size());		//10
		Thread.sleep(2000);
	}


}
