package com.wyx.learn;

/**
 * Created by wangongxin on 17/7/8.
 */
public class ObjectLoclk {
    public void method1() {
        synchronized (this) {//对象锁
            System.out.println("do method1..");
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public void method2() {
        synchronized (ObjectLoclk.class) {//类锁
            System.out.println("do method2..");
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    private Object lock = new Object();

    public void method3() {
        synchronized (lock) {//任意对象锁
            System.out.println("do method3..");
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public static void main(String[] args) {
        final ObjectLoclk objectLoclk = new ObjectLoclk();
        Thread t1 = new Thread(new Runnable() {
            @Override
            public void run() {
                objectLoclk.method1();
            }
        }, "t1");
        Thread t2 = new Thread(new Runnable() {
            @Override
            public void run() {
                objectLoclk.method2();
            }
        }, "t2");
        Thread t3 = new Thread(new Runnable() {
            @Override
            public void run() {
                objectLoclk.method3();
            }
        }, "t3");
        t1.start();
        t2.start();
        t3.start();
    }
}
