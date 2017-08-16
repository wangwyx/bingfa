package com.wyx.lock;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.*;

/**非公平的读-写锁实现
 * Created by Administrator on 2017/8/16.
 * 读写锁是数据库中很常见的锁,又叫共享-排他锁,S锁和X锁。读写锁在大量读少量写的情况下有很高的效率优势
 * 读写锁是基于普通的互斥锁构建出来的更复杂的锁,它有两个基本特点：
 1. 当任一线程持有读锁或写锁时,不允许其他线程再持有写锁
 2. 当任一线程持有写锁时,不允许其他线程再持有读锁
 写锁是排他的,只要有一个线程持有写锁,就不允许其他线程再上锁.读锁是共享的,可以有多个线程持有读锁,但不允许同时持有写锁
 实现思路
 1. 使用一个volatile boolean write标志位来表示是否有线程持有写锁
 2. 使用一个volatile 计数器来记录持有读锁的个数
 3. 使用两个条件来表示条件谓词： existReadCondition, existWriteCondition
 4. 需要一个内部锁来进行线程的同步，这里使用非公平的显式锁ReentrantLock
 5. 当有线程持有写锁时，设置write = true，让其他线程在existWriteCondition上等待
 6. 当有线程持有读锁时，让需要获得写锁的线程在existReadCondition等待，需要读锁的线程可以获得读锁，并设置读锁计数器加1
 7. 释放写锁时，需要唤醒在existWriteCondition上等待的线程
 8. 释放读锁时，设置读锁计数器减1，当读锁计数器等于0时表示当前没有读锁存在，唤醒在existReadCondition条件等待的线程
 9. 值得注意的是这个简单的读-写锁实现，在同一时刻，不管是读锁还是写锁都需要竞争内部锁，也就是说即使是多个读锁并发，同一时刻也只有一个线程能拿到内部锁，只是这个锁的持有时间很短，只设置一个计数器就释放了
 问题:
 非公平的读-写锁在大量读存在的情况下有饥饿的问题，写锁在readCount > 0这个条件谓词下等待，也就是说一旦有读锁存在，写操作就要等待，而读操作一般都是远高于写操作的，并且没有先来先服务的公平性，后来的读操作很可能快于写操作获得锁。写锁有可能长时间的等待锁而不能获取锁。
 */
public class SimpleReadWriteLock implements MyReadWriteLock {
    private final java.util.concurrent.locks.Lock lock;

    private final java.util.concurrent.locks.Condition existReadCondition;

    private final java.util.concurrent.locks.Condition existWriteCondition;

    private final Lock readLock;

    private final Lock writeLock;

    private volatile boolean write;

    private volatile int readCount;
    public SimpleReadWriteLock(){
        lock = new ReentrantLock();
        readLock = new ReadLock();
        writeLock = new WriteLock();
        existReadCondition = lock.newCondition();
        existWriteCondition = lock.newCondition();
        write = false;
        readCount = 0;
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
                readCount ++;
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
                readCount --;
                if(readCount == 0){
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
                while(readCount > 0){
                    try {
                        existReadCondition.await();
                    } catch (InterruptedException e) {
                        throw new RuntimeException("Interrupted");
                    }
                }
                while(write){
                    try {
                        existWriteCondition.await();
                    } catch (InterruptedException e) {
                        throw new RuntimeException("Interrupted");
                    }
                }
                write = true;
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
        return readLock;
    }

    @Override
    public Lock writeLock() {
        return writeLock;
    }
}
