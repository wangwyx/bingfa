package com.wyx.ThreadPool;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.TimeUnit;

/**
 * Created by wangongxin on 17/8/14.
 */
public class ScheduledJob {
    public static void main(String args[]) throws Exception {

        Temp command = new Temp();
        ScheduledExecutorService scheduler = Executors.newScheduledThreadPool(1);

        ScheduledFuture<?> scheduleTask = scheduler.scheduleWithFixedDelay(command, 5, 1, TimeUnit.SECONDS);

    }
}

class Temp extends Thread {
    public void run() {
        System.out.println("run");
    }
}
