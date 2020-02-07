package com.wyx.thread;

public class Test {
    private static int a = 10;
    private static ThreadLocal<Integer> local;
    public static void main(String[] args) {

        Thread A = new Thread(new ThreadA());
        A.start();
        ThreadB B = new ThreadB();
        B.start();

        new Thread(() -> {
            try {
                for (int i = 0; i < 100; i++) {
                    local.set(i);
                    System.out.println(Thread.currentThread().getName() + "====" + local.get());
                    try {
                        Thread.sleep(200);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            } finally {
                local.remove();
            }
        }, "threadLocal1").start();


        new Thread(() -> {
            try {
                for (int i = 0; i < 100; i++) {
                    local.set(100+i);
                    System.out.println(Thread.currentThread().getName() + "====" + local.get());
                    try {
                        Thread.sleep(200);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            } finally {
                local.remove();
            }
        }, "threadLocal2").start();
    }


    static class ThreadA implements Runnable{
        @Override
        public void run() {
            local = new ThreadLocal();
            local.set(a+10);
            System.out.println(local.get()+Thread.currentThread().getName());
            local.remove();
            System.out.println(local.get()+Thread.currentThread().getName());
        }
    }

    static class ThreadB extends Thread{
        @Override
        public void run() {
             System.out.println(local.get()+Thread.currentThread().getName());

        }
    }
}