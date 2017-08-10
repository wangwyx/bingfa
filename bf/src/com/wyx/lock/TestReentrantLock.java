package com.wyx.lock;


import java.util.concurrent.locks.ReentrantLock;

/**
 * Created by Administrator on 2017/8/9.
 */
public class TestReentrantLock implements Runnable{
    ReentrantLock lock = new ReentrantLock();

    public void get() {
        lock.lock();
        System.out.println(Thread.currentThread().getId());
        set();
        lock.unlock();
    }
    public  void set() {
        lock.lock();
        System.out.println(Thread.currentThread().getId());
        lock.unlock();
    }

    @Override
    public void run() {
        get();
    }
    public static void main(String[] args) {
        TestReentrantLock tt = new TestReentrantLock();
        new Thread(tt).start();
        new Thread(tt).start();
        new Thread(tt).start();
        new Thread(tt).start();
    }
}
