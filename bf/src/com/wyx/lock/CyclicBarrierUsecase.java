package com.wyx.lock;

import java.util.concurrent.CyclicBarrier;

/**CyclicBarrier栅栏，可循环使用,与CountDownLatch类似,也是让一组线程等待，然后一起开始往下执行。区别
 * 1. 等待的对象不同。CountDownLatch的一组线程等待的是一个事件，或者说是一个计数器归0的事件。而CyclicBarrier等待的对象是线程，只有线程都到齐了才往下执行
 2. 使用方式不同，这个也是由等待的对象不同引起的，CountDownLatch需要调用await()来让线程等待，调用countDown()来修改状态，直到触发状态为0的事件。而CyclicBarrier只需要调用await()让线程等待，当调用await()方法的线程数满足条件，就自动唤醒所有线程往下执行
 3. CyclicBarrier可以自动循环使用，当一次拦截被打开后，会自动创建下一个拦截。CountDownLatch的计数器归0后不能再次使用
 4. 底层实现不同，CountDownLatch使用AQS来实现底层同步，CyclicBarrier基于更上层的ReetrantLock + Condition条件队列实现
 5. 失效机制不同，在CountDownLatch等待的线程如果被中断或者超时取消，不会影响其他线程。而CyclicBarrier采用all-or-none的机制，要么全部不通过，要么全部都通过，也就是说一旦在CyclicBarrier等待的线程有一个被中断或者超时取消，那么其他所有在这个CyclicBarrier等待的线程都被唤醒，通过栅栏往下执行
 6. CyclicBarrier支持线程全部通过之后的回调功能，通过传入一个Runnable对象，由最后一个到达的线程来执行。而CountDownLatch不支持回调机制
 * Created by Administrator on 2017/8/16.
 * 源码实现：
 * 1.有一个内部类Generation来处理循环使用的问题，维护了一个broker状态表示当前的栅栏是否失效。如果失效，可以重置栅栏的状态。当栅栏被打破时，就设置当前generation的broker为true表示失效，并唤醒所有等待的线程，即all-or-none机制
 * 2.维护了一个ReentrantLock lock来作同步，并创建了一个相关的条件队列Condition，使用Condition的await()方法让线程在同一个条件队列等待，使用Condition.signalAll()唤醒所有在通过一条件队列等待的线程。
 * 3.维护了一个Runnable barrierCommand引用来支持回调功能
 * 4.维护了一个count来计数，当await()方法被调用一次, count就减1，直到count为0打开栅栏。
 * 5.可以看到CyclicBarrier的实例属性都没有使用volatile变量，那它怎么保证状态的可见性呢？CyclicBarrier使用了加显式锁的方式。我们知道显式锁和内置锁一样，都保证了可见性，有序性和原子性。
     5.1. 进入锁相当于读volatile，会清空CPU缓存，强制从内存读取
     5.2. 离开锁相当于写volatile，会把CPU写缓冲区的数据强制刷新到内存
   6.CyclicBarrier常用支持普通的等待和限时的等待。最后都是落到了dowait()方法。dowait方法
     1. 必须先获取锁，保证了可见性，有序性，原子性
     2. 判断当前栅栏的状态，如果已经失效，抛出BrokerBarrierException异常
     3. 如果线程被中断，那么让栅栏失效，会唤醒所有等待线程往下执行
     4. 执行一次dowait就对count减一，用index记录下当前线程执行是的count值作为索引
     5. 如果index == 0表示是最后到达的线程，可以打开栅栏了。首先如果有回调，就执行回调。然后重置栅栏状态，使之可以循环使用，返回0
     6. 如果index不为0，表示不是最后到达的线程，就轮询等待，这里支持了限时操作，使用了Condition条件队列的await()机制。直到超时或者栅栏被正常失效。栅栏失效后会使用Condition来唤醒所有在同一个条件队列等待的线程。
 */
public class CyclicBarrierUsecase {
    private CyclicBarrier barrier = new CyclicBarrier(5, new Runnable(){

        @Override
        public void run() {
            System.out.println("Callback is running");
        }


    });

    public void race() throws Exception{
        System.out.println("Thread " + Thread.currentThread().getName() + " is waiting the resource");
        barrier.await();
        System.out.println("Thread " + Thread.currentThread().getName() + " got the resource");
    }

    public static void main(String[] args) {
        final CyclicBarrierUsecase usecase = new CyclicBarrierUsecase();

        for(int i = 0; i < 12; i++){
            Thread t = new Thread(new Runnable(){

                @Override
                public void run() {
                    try {
                        usecase.race();
                    } catch (Exception e) {
                        // TODO Auto-generated catch block
                        e.printStackTrace();
                    }
                }

            }, String.valueOf(i));
            t.start();
        }
    }
}
