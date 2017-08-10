package com.wyx.CustormerAndProvider;

import java.util.Random;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * Created by wangongxin on 17/8/10.
 */
public class Provider implements Runnable{
    //共享缓冲区
    private BlockingQueue<Data> queue;
    private volatile boolean isRunning = true;
    //id生成器
    private static AtomicInteger count = new AtomicInteger();
    //随机对象
    private static Random r = new Random();

    public Provider(BlockingQueue<Data> queue) {
        this.queue = queue;
    }

    @Override
    public void run() {
        while (isRunning) {
            try {
                Thread.sleep(r.nextInt(1000));
                int id = count.incrementAndGet();
                Data data = new Data(Integer.toString(id), "data" + id);
                System.out.println("当前线程："+Thread.currentThread().getName()+",获取了数据，id为："+id+",进行装载到公共缓冲区中。。。");
                if (this.queue.offer(data,2, TimeUnit.SECONDS)) {
                    System.out.println("提交缓冲区数据失败。。。");
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public void stop() {
        this.isRunning = false;
    }
}
