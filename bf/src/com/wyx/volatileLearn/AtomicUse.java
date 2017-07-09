package com.wyx.volatileLearn;

import com.sun.org.apache.bcel.internal.generic.RET;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * atomic
 * Created by wangongxin on 17/7/9.
 */
public class AtomicUse {
    private static AtomicInteger count = new AtomicInteger(0);
    //synchronized
    public synchronized int multiadd() {
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        count.addAndGet(1);
        count.addAndGet(2);
        count.addAndGet(3);
        count.addAndGet(4);
        return count.get();
    }

    public static void main(String[] args) {
        final AtomicUse au = new AtomicUse();
        List<Thread> ts = new ArrayList<Thread>(0);
        for (int i=0;i<100;i++) {
            ts.add(new Thread(new Runnable() {
                @Override
                public void run() {
                    System.out.println(au.multiadd());
                }
            }, String.valueOf(i)));
        }
        for (Thread t : ts) {
            t.start();
        }
    }
}
