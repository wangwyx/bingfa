package com.wyx.learn;

/**
 * Created by Administrator on 2017/6/16.
 * 当多个线程访问类中run 方法时，以排队的方式处理(根据获得cpu的先后顺序)
 * 一个线程执行synchronized修饰的方法
 *  1.尝试获得锁
 *  2.获得锁后，执行代码，拿不到，多个线程同时不断尝试获得锁，直到某个拿到为止，产生锁竞争问题
 */
public class MyThread extends Thread{
    private int num = 12;
    //synchronized 加锁
    public synchronized void run() {
        num--;
        System.out.println(this.currentThread().getName()+"--num="+num);
    }

    public static void main(String[] args) {
        MyThread myThread = new MyThread();
        Thread t1 = new Thread(myThread, "t1");
        Thread t2 = new Thread(myThread, "t2");
        Thread t3 = new Thread(myThread, "t3");
        Thread t4 = new Thread(myThread, "t4");
        Thread t5 = new Thread(myThread, "t5");
        Thread t6 = new Thread(myThread, "t6");
        Thread t7 = new Thread(myThread, "t7");
        Thread t8 = new Thread(myThread, "t8");
        Thread t9 = new Thread(myThread, "t9");
        Thread t10 = new Thread(myThread, "t10");
        Thread t11 = new Thread(myThread, "t11");
        t1.start();
        t2.start();
        t3.start();
        t4.start();
        t5.start();
        t6.start();
        t7.start();
        t8.start();
        t9.start();
        t10.start();
        t11.start();
    }
}
