package com.wyx.MasterWorker;

import java.util.Random;

/**
 * Created by wangongxin on 17/8/8.
 */
public class Main {
    public static void main(String[] args) {
        Master master = new Master(new Worker(), Runtime.getRuntime().availableProcessors());
        for (int i=0;i<100;i++) {
            Task task = new Task();
            Random r = new Random();
            task.setId(i);
            task.setName("woker" + i);
            task.setPrice(r.nextInt(1000));
            master.submit(task);
        }
        master.execute();
        long start = System.currentTimeMillis();
        while (true) {
            if (master.isComplete()) {
                int res = master.getResult();
                long end = System.currentTimeMillis() - start;
                System.out.println("执行结果"+res+",执行耗时："+end);
                break;
            }
        }
    }
}
