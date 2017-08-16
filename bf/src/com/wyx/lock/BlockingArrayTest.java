package com.wyx.lock;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * Created by Administrator on 2017/8/16.
 * 1. 使用100个线程往阻塞队列里面put() 1到100的数字

 2. 使用100个线程从阻塞队列take一个数
 */
public class BlockingArrayTest {
    public static void main(String[] args) {
        //final BlockingArray<Integer> blockingArray = new BlockingArray<Integer>(10);

        final BlockingArrayWithCondition<Integer> blockingArray = new BlockingArrayWithCondition<Integer>(10);


        final AtomicInteger count = new AtomicInteger(0);

        for(int i = 0; i < 100; i ++){
            Thread t = new Thread(new Runnable(){

                @Override
                public void run() {
                    try {
                        blockingArray.put(count.incrementAndGet());
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }

            });
            t.start();
        }

        for(int i = 0; i < 100; i ++){
            Thread t = new Thread(new Runnable(){
                @Override
                public void run() {
                    try {
                        blockingArray.take();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }

            });
            t.start();
        }

    }
}
