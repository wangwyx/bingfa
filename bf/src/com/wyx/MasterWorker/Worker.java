package com.wyx.MasterWorker;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentLinkedDeque;

/**
 * Created by wangongxin on 17/8/2.
 */
public class Worker implements Runnable{
    private ConcurrentLinkedDeque<Task> workqueue;
    private ConcurrentHashMap<String, Object> resultMap;
    @Override
    public void run() {
        while (true) {
            Task input = this.workqueue.poll();
            if (input == null) break;
            //真正的业务处理
            Object output = MyWorker.handle(input);
            this.resultMap.put(Integer.toString(input.getId()), output);
        }
    }

    private Object handle1(Task input) {
        Object output = null;
        try {
            Thread.sleep(500);//表示处理业务的耗时
            output = input.getPrice();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return output;
    }

    public static Object handle(Task input) {

        return null;
    }

    public void setWorkQueue(ConcurrentLinkedDeque<Task> workqueue) {
        this.workqueue = workqueue;
    }

    public void setResultMap(ConcurrentHashMap<String, Object> resultMap) {
        this.resultMap = resultMap;
    }


}
