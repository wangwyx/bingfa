package com.wyx.lock;

import java.util.concurrent.locks.Lock;

/**
 * Created by Administrator on 2017/8/16.
 */
public interface MyReadWriteLock {
    public Lock readLock();

    public Lock writeLock();
}
