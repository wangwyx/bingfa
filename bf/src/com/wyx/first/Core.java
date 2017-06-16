package com.wyx.first;

import java.util.concurrent.TimeUnit;

/**
 * Created by Administrator on 2016/11/10.
 */
public class Core {
    public static void main(String[] args) {
        UnsafeTask task=new UnsafeTask();
        for (int i=0; i<10; i++){
            Thread thread=new Thread(task);
            thread.start();
            try {
                TimeUnit.SECONDS.sleep(2);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
