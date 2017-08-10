package com.wyx.CustormerAndProvider;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.LinkedBlockingDeque;

/**
 * Created by wangongxin on 17/8/10.
 */
public class Main {
    public static void main(String[] args) {
        //内存缓冲区
        BlockingQueue<Data> queue = new LinkedBlockingDeque<Data>(10);
        //生存者
        Provider p1 = new Provider(queue);
        Provider p2 = new Provider(queue);
        Provider p3 = new Provider(queue);
        Provider p4 = new Provider(queue);
        //消费者
        Consumer c1 = new Consumer(queue);
        Consumer c2 = new Consumer(queue);
        Consumer c3 = new Consumer(queue);
        Consumer c4 = new Consumer(queue);
        //创建线程池
        ExecutorService cachepool = Executors.newCachedThreadPool();
        cachepool.execute(p1);
        cachepool.execute(p2);
        cachepool.execute(p3);
        cachepool.execute(p4);
        cachepool.execute(c1);
        cachepool.execute(c2);
        cachepool.execute(c3);
        cachepool.execute(c4);

        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        p1.stop();
        p2.stop();
        p3.stop();
        p4.stop();

        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
