package com.wyx.queue;

import java.util.PriorityQueue;
import java.util.concurrent.*;

/**
 * Created by wangongxin on 17/7/9.
 */
public class UseQueue {
    public static void main(String[] args) {

        ArrayBlockingQueue<String> array = new ArrayBlockingQueue<String>(10);
        try {
            array.offer("a",2, TimeUnit.SECONDS);
            array.add("b");
            array.add("c");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        /**
         * 阻塞队列
         */
        LinkedBlockingQueue<String> link = new LinkedBlockingQueue<String>(10);
        SynchronousQueue<String> synchronousQueue = new SynchronousQueue<String>();
        PriorityBlockingQueue<String> p = new PriorityBlockingQueue<String>();
        DelayQueue d = new DelayQueue();
    }

}
