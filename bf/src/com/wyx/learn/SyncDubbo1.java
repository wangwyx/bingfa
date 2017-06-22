package com.wyx.learn;

/**
 * Created by Administrator on 2017/6/16.
 */
public class SyncDubbo1 {
    public void method1(){
        System.out.println("method1..");
        method2();
    }
    public void method2(){
        System.out.println("method2..");
        method3();
    }
    public void method3(){
        System.out.println("method3..");
    }

    public static void main(String[] args) {
        final SyncDubbo1 sy = new SyncDubbo1();
        Thread t1 = new Thread(new Runnable() {
            @Override
            public void run() {
                sy.method1();
            }
        },"t1");
        t1.start();
    }
}
