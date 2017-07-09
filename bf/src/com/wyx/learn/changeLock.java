package com.wyx.learn;

/**
 * Created by wangongxin on 17/7/8.
 */
public class changeLock {
    private String lock = "lock";
    public void methtod() {
        synchronized (lock) {
            try {
                System.out.println("当前线程："+ Thread.currentThread().getName()+"开始");
                lock = "change lock";
                Thread.sleep(2000);
                System.out.println("当前线程："+ Thread.currentThread().getName()+"结束");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public static void main(String[] args) {
        final changeLock changeLock = new changeLock();
        Thread t1 = new Thread(new Runnable() {
            @Override
            public void run() {
                changeLock.methtod();
            }
        }, "t1");
        Thread t2 = new Thread(new Runnable() {
            @Override
            public void run() {
                changeLock.methtod();
            }
        }, "t2");
        t1.start();
        try {
            Thread.sleep(100);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        t2.start();
    }
}
