package com.wyx.waitAndNotify;

import com.sun.javafx.scene.control.skin.VirtualFlow;

import java.util.ArrayList;
import java.util.List;

/**
 * 使用wait。notify实现线程间的通信，两个都是object的方法。必需配合synchronized使用。wait释放锁，notify不释放
 * Created by wangongxin on 17/7/9.
 */
public class ListAdd1 {
    private volatile static List list = new ArrayList();

    public void add() {
        list.add("sdf");
    }

    public int size() {
        return list.size();
    }

    public static void main(String[] args) {
        final ListAdd1 list1 = new ListAdd1();
        Thread t1 = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    for (int i = 0; i < 10; i++) {
                        list1.add();
                        System.out.println("当前线程：" + Thread.currentThread().getName() + "添加了一个元素");
                        Thread.sleep(1000);
                    }

                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }, "t1");
        Thread t2 = new Thread(new Runnable() {
            @Override
            public void run() {
                while (true) {
                    if (list1.size() == 5) {
                        System.out.println("当前线程收到通知：" + Thread.currentThread().getName() + "list1 size 为5，线程停止。。");
                        throw new RuntimeException();
                    }
                }
            }
        }, "t2");
        t1.start();
        t2.start();
    }
}
