package com.wyx.lock;

/**
 * Created by Administrator on 2017/8/9.
 */
public class TestReteenLock implements Runnable {

    public synchronized  void set() {
        System.out.println(Thread.currentThread().getId());
    }

    public synchronized void get() {
        System.out.println(Thread.currentThread().getId());
        set();
    }
    @Override
    public void run() {
        get();
    }

    public static void main(String[] args) {
        TestReteenLock tt = new TestReteenLock();
        new Thread(tt).start();
        new Thread(tt).start();
        new Thread(tt).start();
        new Thread(tt).start();
    }
}
