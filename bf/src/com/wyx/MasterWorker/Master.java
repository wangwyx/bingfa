package com.wyx.MasterWorker;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentLinkedDeque;

/**
 * Created by wangongxin on 17/8/2.
 */
public class Master {
    //1.应该有一个装任务的集合
    private ConcurrentLinkedDeque<Task> workqueue = new ConcurrentLinkedDeque<Task>();
    //2.装载线程worker
    private HashMap<String, Thread> workers = new HashMap<String,Thread>();
    //3.使用一个容器装载每个worker并非执行任务的结果集
    private ConcurrentHashMap<String, Object> resultMap = new ConcurrentHashMap<String, Object>();

    //4.构造方法
    public Master(Worker worker, int workCount) {
        //每一个worker，都需要有Master的引用，workqueue用于任务的领取，resultMap用于任务的提交
        worker.setWorkQueue(this.workqueue);
        worker.setResultMap(this.resultMap);
        for (int i=0;i<workCount;i++) {
            //key 表示每一个worker的名字,value表示线程执行对象
            workers.put("worker"+Integer.toString(i),new Thread(worker));
        }
    }

    //5.提交方法
    public void submit(Task task) {
        this.workqueue.add(task);
    }

    //6. 需要有一个执行的方法（启动应用程序，让所有的worker工作）
    public void execute() {
        for (Map.Entry<String, Thread> me : workers.entrySet()) {
            me.getValue().start();
        }
    }
    //7.判读线程是否执行完毕
    public boolean isComplete() {
        for (Map.Entry<String, Thread> me : workers.entrySet()) {
            if (me.getValue().getState() != Thread.State.TERMINATED) {
                return false;
            }
        }
        return true;
    }
    //8 返回结果集数据
    public int getResult() {
        int ret = 0;
        for (Map.Entry<String, Object> me : resultMap.entrySet()) {
            ret += (Integer)me.getValue();
        }
        return ret;
    }
}
