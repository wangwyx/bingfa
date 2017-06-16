package com.wyx.first;

import java.util.concurrent.TimeUnit;

/**
 * Created by Administrator on 2016/11/10.
 */
public class RunnableCore {
    public static void main(String[] args) {
        SafeTask task=new SafeTask();
        for (int j=0; j<10; j++){
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
