package com.wyx.learn;

/**多个线程多个锁：每个线程都可以拿到自己指定的锁，获得锁后，执行synchronized方法体的内容
 * synchronized获得的锁都是对象锁
 * 示例代码哪个线程先执行，哪个线程就持有该方法所属对象的锁，两个对象，线程获得的就是两个不同的锁，互不影响
 * 获得相同锁：在静态方法加 synchronized 表示锁定.class类，类级别锁(独占.class类)
 * Created by Administrator on 2017/6/16.
 *
 */
public class MutiThread {
    private static int num = 0;
    private  int num2 = 0;
    public static synchronized void printNum(String tag){

        try {
            if (tag.equals("a")) {
                num =100;
                System.out.println("tag a,set num over!");
                Thread.sleep(1000);
            }else {
                num = 200;
                System.out.println(Thread.currentThread().getName()+",tag b,set num over!");
            }
            System.out.println("tag ="+ tag+",num ="+num);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public synchronized void printNum2(String tag){

        try {
            if (tag.equals("a")) {
                num2 =100;
                System.out.println("tag a,set num over!");
                Thread.sleep(1000);
            }else {
                num2 = 200;
                System.out.println(Thread.currentThread().getName()+",tag b,set num over!");
            }
            System.out.println("tag ="+ tag+",num ="+num);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
    public static void main(String[] args) {
        final MutiThread m1 = new MutiThread();
        final MutiThread m2 = new MutiThread();
        final MutiThread m3 = new MutiThread();
        final MutiThread m4 = new MutiThread();
        Thread t1 = new Thread(new Runnable() {
            @Override
            public void run() {
                m1.printNum("a");
            }
        });
        Thread t2 = new Thread(new Runnable() {
            @Override
            public void run() {
                m2.printNum("b");
            }
        },"t2");
        Thread t3 = new Thread(new Runnable() {
            @Override
            public void run() {
                m3.printNum2("a");
            }
        },"t3");
        Thread t4 = new Thread(new Runnable() {
            @Override
            public void run() {
                m4.printNum2("b");
            }
        },"t4");
        t1.start();
        t2.start();
        t3.start();
        t4.start();
    }
}
