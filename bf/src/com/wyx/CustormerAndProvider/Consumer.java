package com.wyx.CustormerAndProvider;

import java.util.Random;
import java.util.concurrent.BlockingQueue;

/**
 * Created by wangongxin on 17/8/10.
 */
public class Consumer implements Runnable{
    private BlockingQueue<Data> queue;
    //随机对象
    private static Random r = new Random();
    public Consumer(BlockingQueue<Data> queue) {
        this.queue = queue;
    }

    @Override
    public void run() {
        while (true) {
            try {
                Data data = this.queue.take();
                Thread.sleep(r.nextInt(1000));
                System.out.println("当前线程："+Thread.currentThread().getName()+",消费成功，消费数据ID为 :"+data.getId());
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
