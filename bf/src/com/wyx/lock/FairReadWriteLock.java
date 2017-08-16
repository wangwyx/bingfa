package com.wyx.lock;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**公平的读写锁，优先让写锁能更快地获得锁，写锁尝试获得锁时，读锁会进入等待，让写锁先获得锁
 * Created by Administrator on 2017/8/16.
 * 实现思路：
 * 1. 使用公平的显式锁作为内部锁，保证先来先服务的公平性
 2. 使用一个readAccquired和readReleased计数器来记录获取读锁和释放读锁的线程个数
 3. 当readAccquired == readReleased时表示读锁已经全部释放，可以获得写锁
 4. 最大的改进在这里，获取写锁时分为两步，第一步当没有写锁存在时，就设置write标志为true表示要获取写锁。当write为true时，后来的读锁就一直等待existWriteConditiont条件释放，而不能增加readAccquire计数器。当之前已经获得的读锁都释放后，写锁获得锁。只有等待写锁释放后，后续的读锁才能继续操作。
 它的改进主要是在readAccquired == readRelease 条件谓词等待，而不是readCount > 0条件谓词等待。readAccquired的数量即肯定小于readCount，而且只要之前没有写锁存在，就优先让写锁获取。能够保证写锁快速获得锁
 */
public class FairReadWriteLock implements MyReadWriteLock {

    private final java.util.concurrent.locks.Lock lock;

    private final java.util.concurrent.locks.Condition existReadCondition;

    private final java.util.concurrent.locks.Condition existWriteCondition;

    private final Lock readLock;

    private final Lock writeLock;

    private volatile boolean write;

    private volatile int readAccquired;

    private volatile int readReleased;

    public FairReadWriteLock(){
        lock = new ReentrantLock(true);
        existReadCondition = lock.newCondition();
        existWriteCondition = lock.newCondition();
        readLock = new ReadLock();
        writeLock = new WriteLock();
        write = false;
        readAccquired = 0;
        readReleased = 0;
    }

    private class ReadLock implements Lock{

        @Override
        public void lock() {
            lock.lock();
            try{
                while(write){
                    try {
                        existWriteCondition.await();
                    } catch (InterruptedException e) {
                        throw new RuntimeException("Interrupted");
                    }
                }
                readAccquired ++;
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
                readReleased ++;
                if(readReleased == readAccquired){
                    existReadCondition.signalAll();
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

    private class WriteLock implements Lock{

        @Override
        public void lock() {
            lock.lock();
            try{
                while(write){
                    try {
                        existWriteCondition.await();
                    } catch (InterruptedException e) {
                        throw new RuntimeException("Interrupted");
                    }
                }
                // 让新加入的读锁不能增加readAccquired
                write = true;
                while(readAccquired != readAccquired){
                    try {
                        existReadCondition.await();
                    } catch (InterruptedException e) {
                        throw new RuntimeException("Interrupted");
                    }
                }
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
                write = false;
                existWriteCondition.signalAll();
            }finally{
                lock.unlock();
            }
        }

        @Override
        public Condition newCondition() {
            return null;
        }

    }
    @Override
    public Lock readLock() {
        return null;
    }

    @Override
    public Lock writeLock() {
        return null;
    }
}
