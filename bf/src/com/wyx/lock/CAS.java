package com.wyx.lock;

/**
 * Created by Administrator on 2017/8/15.
 * CAS(Compare and Swap)目标地址的值Target，期望值Expected，实际值Real，
 1. 只有当目标值T == 期望值E时，才会把目标值T设置为实际值R，否则不改变目标值
 2. 不管目标值是否改变，都返回之前的目标值T
 */
public class CAS {
    private int value;

    public synchronized int get(){
        return value;
    }

    public synchronized int compareAndSwap(int expected, int real){
        int oldValue = value;
        if(value == expected){
            value = real;
        }
        return oldValue;
    }

    public synchronized boolean compareAndSet(int expected, int real){
        return (expected == compareAndSwap(expected, real));
    }
}
