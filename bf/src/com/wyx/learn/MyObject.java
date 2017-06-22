package com.wyx.learn;

/**对象锁的同步异步问题
 * Created by Administrator on 2017/6/16.
 */
public class MyObject {
    public synchronized void method1() {
        try {
            System.out.println(Thread.currentThread().getName());
            Thread.sleep(4000);
            System.out.println("a");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
    /**synchronized*/
    public synchronized void method2(){
        System.out.println(Thread.currentThread().getName());
    }

    public static void main(String[] args) {
        final MyObject mo = new MyObject();
        /**
         * t1先持有MyObject mo对象的lock锁,t2可以使用异步的方式调用对象中的非synchronized修饰的方法
         * t1先持有MyObject mo对象的lock锁,t2如果调用对象中的同步方法，需要等待，也就是同步
         */
        Thread t1 = new Thread(new Runnable() {
            @Override
            public void run() {
                mo.method1();
            }
        },"t1");
        Thread t2 = new Thread(new Runnable() {
            @Override
            public void run() {
                mo.method2();
            }
        },"t2");
        t1.start();
        t2.start();
    }
}
