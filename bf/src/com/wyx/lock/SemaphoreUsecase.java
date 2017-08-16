package com.wyx.lock;

import java.util.concurrent.Semaphore;

/**信号量
 * Created by Administrator on 2017/8/16.
 * Semaphore表示了一种可以同时有多个线程进入临界区的同步器，它维护了一个状态表示可用的票据，
 * 只有拿到了票据的线程尽可以进入临界区，否则就等待，直到获得释放出的票据。
 * Semaphore常用在资源池中来管理资源。当状态只有1个0两个值时，它退化成了一个互斥的同步器，类似锁。
 */
public class SemaphoreUsecase {
    private Semaphore semaphore = new Semaphore(2);

    public void race(){
        System.out.println("Thread " + Thread.currentThread().getName() + " is waiting the resource");
        semaphore.acquireUninterruptibly();
        try{
            System.out.println("Thread " + Thread.currentThread().getName() + " got the resource");
            try {
                Thread.sleep(3000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }finally{
            System.out.println("Thread " + Thread.currentThread().getName() + " is releasing the resource");
            semaphore.release();
        }
    }

    public static void main(String[] args) {
        final SemaphoreUsecase usecase = new SemaphoreUsecase();

        for(int i = 0; i < 10; i++){
            Thread t = new Thread(new Runnable(){

                @Override
                public void run() {
                    usecase.race();
                }

            }, String.valueOf(i));
            t.start();
        }
    }
}
