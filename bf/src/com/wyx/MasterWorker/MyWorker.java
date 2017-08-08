package com.wyx.MasterWorker;

/**
 * Created by wangongxin on 17/8/8.
 */
public class MyWorker extends Worker{

    public static Object handle(Task input) {
        Object output = null;
        try {
            Thread.sleep(500);//表示处理业务的耗时
            output = input.getPrice();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return output;
    }
}
