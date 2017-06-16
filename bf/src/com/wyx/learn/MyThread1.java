package com.wyx.learn;

/**
 * Created by Administrator on 2017/6/16.
 */
public class MyThread1 implements Runnable{
    private int num = 12;
    @Override
    public synchronized void run() {
        num--;
        System.out.println(Thread.currentThread().getName()+"--num="+num);
    }

    public static void main(String[] args) {
        MyThread1 myThread = new MyThread1();
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
