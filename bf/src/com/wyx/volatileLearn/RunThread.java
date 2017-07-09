package com.wyx.volatileLearn;

/**
 * Created by wangongxin on 17/7/8.
 * volatile作用使变量在多个线程间可见
 * 每个线程都有一块工作区，其中存放着所有线程共享的主内存中的变量值的拷贝。当线程执行时，他在自己的工作内存区中操作这些变量。为了存取一个共享的变量，
 * 一个线程通常先获取锁并去清除它的内存工作区，把这些共享变量从所有线程的共享内存区中正确的装入到他自己所在的工作内存区中，当线程解锁时保证该工作内存区中变量的值写回到
 * 内存中
 */
public class RunThread extends Thread {
    /**volatile*/
    private volatile boolean isRunning = true;

    public void setRunning(boolean running) {
        isRunning = running;
    }

    @Override
    public void run() {
        System.out.println("进入run 方法..");
        while (isRunning) {
            //....
        }
        System.out.println("线程终止");
    }

    public static void main(String[] args) throws InterruptedException {
        RunThread runThread = new RunThread();
        runThread.start();
        Thread.sleep(3000);
        runThread.setRunning(false);
        System.out.println("isRunning 的值已经设置了false");
//        Thread.sleep(1000);
        System.out.println(runThread.isRunning);
    }
}
