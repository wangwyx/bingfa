package com.wyx.thread;

public class Demo06 {

    static Object object = new Object();

    public static class T1 extends Thread {

        //start方法是启动一个线程，run方法只会在当前线程中串行的执行run方法中的代码。
        @Override
        public void run() {
            synchronized (object) {
                System.out.println(System.currentTimeMillis() + ":T1 start!");
                try {
                    System.out.println(System.currentTimeMillis() + ":T1 wait for object");
                    object.wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println(System.currentTimeMillis() + ":T1 end!");
            }
        }
    }


    public static class T2 extends Thread {
        @Override
        public void run() {
            synchronized (object) {
                System.out.println(System.currentTimeMillis() + ":T2 start，notify one thread! ");
                object.notify();
                System.out.println(System.currentTimeMillis() + ":T2 end!");
                try {
                    Thread.sleep(2000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }


    public static void main(String[] args) throws InterruptedException {
        new T1().start();//start方法是启动一个线程，run方法只会在当前线程中串行的执行run方法中的代码。
        T1.interrupted();//线程中断,并不会使线程立即退出
        new T2().start();
    }

}