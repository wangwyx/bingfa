package com.wyx.learn;

import com.sun.istack.internal.FinalArrayList;

/**
 * 执行一个队列任务，很多对象都在等待一个对象正确执行释放锁，但是第一个对象没有正常执行出现异常，导致业务逻辑没有执行完毕，就释放了锁
 * Created by Administrator on 2017/6/23.
 */
public class SyncException {
    private int i = 0;

    public synchronized void operation() {
        while (true) {
            try {
                i++;
                Thread.sleep(200);
                System.out.println(Thread.currentThread().getName()+",i="+i);
                if (i == 10) {
                    Integer.parseInt("a");
                }
            } catch (Exception e) {//InterruptedException
                e.printStackTrace();
                System.out.println("log info i="+i);
                //continue;
            }
        }
    }

    public static void main(String[] args) {
        final SyncException syncException = new SyncException();
        Thread t1 = new Thread(new Runnable() {
            @Override
            public void run() {
                syncException.operation();
            }
        }, "t1");
        t1.start();
    }
}
