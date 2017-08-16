package com.wyx.lock;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**可重入锁指的是如果一个线程已经获得了一个锁，那么它可以多次进入这个锁，当然前提是线程需要先获得这个锁
 * Java的内置锁就是可重入锁，使用synchronized关键字可以启用内置锁机制
 * 实现:它使用一个计数器来记录一个线程进入锁的次数，每次进入锁计数器就加1，释放锁减1，
 * 直到计数器为0时表示真正释放了锁。其他锁看到计数器不为0时就知道有其他线程已经获得了锁，就需要等待
 * Created by Administrator on 2017/8/16.
 * 1. 使用一个Thread引用指向获得锁的线程
 2. 使用一个计数器记录一个线程进入锁的次数，当计数器为0时表示锁是空闲的
 3. 使用一个内部锁Lock来同步线程
 4. 使用一个isHoldZero的条件来进行条件队列操作
 5. 当获得锁的线程是自己时，只修改计数器的值，直接获得锁
 6. 当获得锁的线程不是自己时，需要在holdCount !=0 这个条件谓词上等待，直到计数器归0，再次竞争锁
 7. 释放锁时计数器减1，当计数器为0时，唤醒在条件队列中等待的线程
 */
public class SimpleReentrantLock implements Lock{
    // 指向已经获得锁的线程
    private volatile Thread exclusiveOwnerThread;

    // 记录获取了同一个锁的次数
    private volatile int holdCount;

    private final java.util.concurrent.locks.Lock lock;

    // 是否是自己获得锁的条件
    private final Condition isCountZero;

    public SimpleReentrantLock(){
        lock = new ReentrantLock();
        isCountZero = lock.newCondition();
        holdCount = 0;
    }
    @Override
    public void lock() {
        lock.lock();
        try{
            // 当前线程的引用
            Thread currentThread = Thread.currentThread();
            // 如果获得锁的线程是自己，那么计数器加1，直接返回
            if(exclusiveOwnerThread == currentThread){
                holdCount ++;
                return;
            }

            while(holdCount != 0){
                try {
                    isCountZero.await();
                } catch (InterruptedException e) {
                    throw new RuntimeException("Interrupted");
                }
            }
            // 将exclusiveOwnerThread设置为自己
            exclusiveOwnerThread = currentThread;
            holdCount ++;
        }finally{
            lock.unlock();
        }
    }

    @Override
    public void lockInterruptibly() throws InterruptedException {

    }

    @Override
    public boolean tryLock() {
        return false;
    }

    @Override
    public boolean tryLock(long time, TimeUnit unit) throws InterruptedException {
        return false;
    }

    @Override
    public void unlock() {
        lock.lock();
        try{
            holdCount --;
            if(holdCount == 0){
                isCountZero.signalAll();
            }
        }finally{
            lock.unlock();
        }
    }


    @Override
    public Condition newCondition() {
        return null;
    }
}
