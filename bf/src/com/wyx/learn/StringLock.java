package com.wyx.learn;

/**
 * 注意string常量池的缓冲功能
 * Created by wangongxin on 17/7/8.
 * 视频上一直是ti开始没有结束
 */
public class StringLock {
    public void methtod() {
        synchronized ("字符串常量") {
            try {
                System.out.println("当前线程："+ Thread.currentThread().getName()+"开始");
                Thread.sleep(1000);
                System.out.println("当前线程："+ Thread.currentThread().getName()+"结束");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public static void main(String[] args) {
        final StringLock stringLock = new StringLock();
        Thread t1 = new Thread(new Runnable() {
            @Override
            public void run() {
                stringLock.methtod();
            }
        }, "t1");
        Thread t2 = new Thread(new Runnable() {
            @Override
            public void run() {
                stringLock.methtod();
            }
        }, "t2");
        t1.start();
        t2.start();
    }
}
