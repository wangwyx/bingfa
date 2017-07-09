package com.wyx.learn;

/**
 * synchronized的锁重入
 * 当一个线程得到一个对象的锁后，再次请求对象时可以再次得到该锁的对象
 * Created by Administrator on 2017/6/16.
 */
public class SyncDubbo2 {
    static class Main{
        public int i=10;

        public synchronized void operationSup() {
            i--;
            System.out.println("main print i="+i);
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    static class Sub extends Main {
        public synchronized void operationSub() {

            try {
                while (i > 0) {
                    i--;
                    System.out.println("sub print i="+i);
                    Thread.sleep(100);
                    this.operationSup();
                }

            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public static void main(String[] args) {
        Thread t1 = new Thread(new Runnable() {
            @Override
            public void run() {
                Sub sub = new Sub();
                sub.operationSub();
            }
        }, "t1");
        t1.start();
    }
}
