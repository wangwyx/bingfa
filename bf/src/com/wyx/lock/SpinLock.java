package com.wyx.lock;

import java.util.concurrent.atomic.AtomicReference;

/**
 * Created by Administrator on 2017/8/9.
 * 自旋锁是采用让当前线程不停地的在循环体内执行实现的，当循环的条件被其他线程改变时 才能进入临界区
 * 由于自旋锁只是将当前线程不停地执行循环体，不进行线程状态的改变，所以响应速度更快。
 * 但当线程数不停增加时，性能下降明显，因为每个线程都需要执行，占用CPU时间。如果线程竞争不激烈，
 * 并且保持锁的时间段。适合使用自旋锁。
 注：该例子为非公平锁，获得锁的先后顺序，不会按照进入lock的先后顺序进行。
 */
public class SpinLock {
    private AtomicReference<Thread> sign = new AtomicReference<>();

    public void lock() {
        Thread current = Thread.currentThread();
        while (!sign.compareAndSet(null, current)) {

        }
    }

    public void unlock() {
        Thread current = Thread.currentThread();
        sign.compareAndSet(null, current);
    }
}
