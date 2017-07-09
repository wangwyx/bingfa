package com.wyx.waitAndNotify;

import sun.java2d.loops.GraphicsPrimitive;
import sun.text.normalizer.UBiDiProps;

import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * Created by wangongxin on 17/7/9.
 */
public class MyQueue {
    //1.需要一个装载元素的集合
    private final LinkedList<Object> link = new LinkedList<Object>();
    //2.需要一个计数器
    private AtomicInteger count = new AtomicInteger(0);
    //3.需要制定上限和下限
    private final int minSize = 0;
    private final int maxSize ;

    public MyQueue(int size) {
        this.maxSize = size;
    }
    //4.加锁
    private final Object lock  = new Object();

    public void put(Object obj) {

        synchronized (lock) {
            while (this.maxSize == count.get()) {
                try {
                    lock.wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            link.add(obj);
            count.incrementAndGet();

            //通知另外一个线程
            lock.notify();
            System.out.println("新加入的元素为："+obj);
        }
    }

    public Object take() {
        Object ret = null;
        synchronized (lock) {
            while (count.get() == this.minSize) {
                try {
                    lock.wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            ret = link.removeFirst();
            count.decrementAndGet();
            //唤醒另外一个线程
            lock.notify();

        }
        return ret;
    }

    public int getSize() {
        return this.count.get();
    }

    public static void main(String[] args) {
        final MyQueue my = new MyQueue(5);
        my.put("a");
        my.put("b");
        my.put("c");
        my.put("d");
        my.put("e");
        System.out.println("当前容器的长度："+my.getSize());
        Thread t1 = new Thread(new Runnable() {
            @Override
            public void run() {
                my.put("f");
                my.put("g");
            }
        }, "t1");
        t1.start();
        Thread t2 = new Thread(new Runnable() {
            @Override
            public void run() {
                Object o1 = my.take();
                System.out.println("移除的元素为：" + o1);
                Object o2 = my.take();
                System.out.println("移除的元素为：" + o2);
            }
        }, "t2");

        try {
            TimeUnit.SECONDS.sleep(2);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        t2.start();
    }
}
