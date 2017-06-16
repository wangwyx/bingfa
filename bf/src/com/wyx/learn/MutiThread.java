package com.wyx.learn;

/**
 * Created by Administrator on 2017/6/16.
 */
public class MutiThread {
    private int num = 0;
    public  void printNum(String tag){

        try {
            if (tag.equals("a")) {
                num =100;
                System.out.println("tag a,set num over!");
                Thread.sleep(1000);
            }else {
                num = 200;
                System.out.println("tag b,set num over!");
            }
            System.out.println("tag ="+ tag+",num ="+num);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        final MutiThread m1 = new MutiThread();
        final MutiThread m2 = new MutiThread();
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
        });
        t1.start();
        t2.start();
    }
}
