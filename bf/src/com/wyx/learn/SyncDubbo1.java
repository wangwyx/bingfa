package com.wyx.learn;

/**synchronized的重入
 * Created by Administrator on 2017/6/16.
 */
public class SyncDubbo1 {
    public synchronized void method1(){
        System.out.println("method1..");
        method2();
    }
    public synchronized void method2(){
        System.out.println("method2..");
        method3();
    }
    public synchronized void method3(){
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
