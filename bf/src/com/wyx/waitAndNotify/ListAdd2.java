package com.wyx.waitAndNotify;

import java.util.ArrayList;
import java.util.List;

/**
 * 使用wait。notify实现线程间的通信，两个都是object的方法。必需配合synchronized使用。wait释放锁，notify不释放
 * 产生不实时的问题
 * Created by wangongxin on 17/7/9.
 */
public class ListAdd2 {
    private volatile static List list = new ArrayList();

    public void add() {
        list.add("sdf");
    }

    public int size() {
        return list.size();
    }

    public static void main(String[] args) {
        final ListAdd2 list2 = new ListAdd2();
        final Object lock = new Object();
        Thread t1 = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    synchronized (lock) {
                        for (int i = 0; i < 10; i++) {
                            list2.add();
                            System.out.println("当前线程：" + Thread.currentThread().getName() + "添加了一个元素");
                            Thread.sleep(1000);
                            if (list2.size() == 5) {
                                System.out.println("已经发出通知。。");
                                lock.notify();
                            }
                        }
                    }


                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }, "t1");
        Thread t2 = new Thread(new Runnable() {
            @Override
            public void run() {
                synchronized (lock) {
                    if (list2.size() != 5) {
                        try {
                            lock.wait();
                        } catch (InterruptedException e) {

                        }

                    }
                    System.out.println("当前线程收到通知：" + Thread.currentThread().getName() + "收到通知，线程停止。。");
                    throw new RuntimeException();
                }

            }
        }, "t2");
        t2.start();
        t1.start();
    }
}
