package com.wyx.lock;

import java.util.concurrent.locks.ReadWriteLock;

/**
 * Created by Administrator on 2017/8/9.
 */
public class TestReentrantReadWriteLock {
    public static void main(String[] args) {

    }
}

class User implements Runnable {

    private String name;                 //用户名
    private MyCount myCount;         //所要操作的账户
    private int iocash;                 //操作的金额，当然有正负之分了
    private ReadWriteLock myLock;                 //执行操作所需的锁对象
    private boolean ischeck;         //是否查询

    public User(int iocash, boolean ischeck, MyCount myCount, ReadWriteLock myLock, String name) {
        this.iocash = iocash;
        this.ischeck = ischeck;
        this.myCount = myCount;
        this.myLock = myLock;
        this.name = name;
    }

    @Override
    public void run() {

    }
}

class MyCount{
    private String oid;         //账号
    private int cash;             //账户余额



    public int getCash() {
        return cash;
    }

    public void setCash(int cash) {
        this.cash = cash;
    }

    public String getOid() {
        return oid;
    }

    public void setOid(String oid) {
        this.oid = oid;
    }

    public MyCount(int cash, String oid) {

        this.cash = cash;
        this.oid = oid;
    }
    @Override
    public String toString() {
        return "MyCount{" +
                "oid='" + oid + '\'' +
                ", cash=" + cash +
                '}';
    }
}