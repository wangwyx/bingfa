package com.wyx.volatileLearn;

import javax.sound.midi.Soundbank;
import java.util.concurrent.atomic.AtomicInteger;

/**不具备原子性
 * Created by wangongxin on 17/7/9.
 */
public class VolatileNoAtomic extends Thread{
    private static volatile int count;
    private static AtomicInteger count2 = new AtomicInteger(0);

    private static void addCount() {
        for (int i=0;i<1000;i++) {
            count++;
            count2.incrementAndGet();
        }
        System.out.println(count);
        System.out.println(count2);
    }

    @Override
    public void run() {
        addCount();
    }

    public static void main(String[] args) {
        VolatileNoAtomic[] arr = new VolatileNoAtomic[10];

        for (int i=0;i<10;i++) {
            arr[i] = new VolatileNoAtomic();
        }
        for (int i=0;i<10;i++) {
            arr[i].start();
        }

    }
}
