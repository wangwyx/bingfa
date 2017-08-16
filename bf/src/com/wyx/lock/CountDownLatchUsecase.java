package com.wyx.lock;

import java.util.concurrent.CountDownLatch;

/**CountDownLatch闭锁也是基于AQS实现的一种同步器，它表示了“所有线程都等待，直到锁打开才继续执行”的含义
 * Created by Administrator on 2017/8/16.
 * Semaphore的获取和释放操作都会修改状态，都可能让自己或者其他线程立刻拿到锁。而闭锁的获取操作只判断状态是否为0，不修改状态本身，闭锁的释放操作会修改状态，每次递减1，直到状态为0。
 正常情况下，闭锁的获取操作只是等待，不会立刻让自己获得锁，直到释放操作把状态变为0
 闭锁可以用来实现很多场景，比如：
 1. 某个服务依赖于其他服务的启动才能启动，就可以让这个服务在其他服务状态的闭锁上等待
 2. 某个游戏，必须等所有就绪者都到达才能开始游戏
 3. 启动一组相关的线程
 4. 等待一组相关线程结束
 功能：1.  创建1个二元闭锁startLatch，只有1和0两种状态，也就是说只执行一次countDown()就可以打开闭锁。这个startLatch用来阻塞线程，直到主线程说可以开始了

 2.  创建1个状态为n的endLatch，线程执行完就调用一次countDown，主线程在endLatch阻塞，直到n个线程都执行了countDown()报数，主线程才打印结束
 */
public class CountDownLatchUsecase {
    private int nThreads;

    private CountDownLatch startLatch;

    private CountDownLatch endLatch;

    public CountDownLatchUsecase(int n){
        this.nThreads = n;
        startLatch = new CountDownLatch(1);
        endLatch = new CountDownLatch(nThreads);
    }

    public void race() throws InterruptedException{
        System.out.println("Thread " + Thread.currentThread().getName() + " is waiting the resource");
        startLatch.await();
        System.out.println("Thread " + Thread.currentThread().getName() + " got the resource");
        endLatch.countDown();
    }

    public void start(){
        startLatch.countDown();
    }

    public void end() throws InterruptedException{
        endLatch.await();
    }

    public static void main(String[] args) throws InterruptedException {
        final CountDownLatchUsecase usecase = new CountDownLatchUsecase(10);
        for(int i = 0; i < 10; i++){
            Thread t = new Thread(new Runnable(){

                @Override
                public void run() {
                    try {
                        usecase.race();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }

            }, String.valueOf(i));
            t.start();
        }

        Thread.sleep(3000);

        System.out.println("Now start!!!");
        usecase.start();
        usecase.end();
        System.out.println("All Thread finished");
    }
}
