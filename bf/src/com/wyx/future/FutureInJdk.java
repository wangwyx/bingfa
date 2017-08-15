package com.wyx.future;

import java.util.concurrent.*;

/**
 * Created by Administrator on 2017/8/11.
 */
public class FutureInJdk {
    public static void main(String[] args) {
        FutureTask task = new FutureTask(new RealData1("data1"));
        ExecutorService executorService = Executors.newFixedThreadPool(1);
        executorService.submit(task);
        try {
            Thread.sleep(2000);
            System.out.println(task.get());
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
    }

}

class RealData1 implements Callable {

    private String data;

    public RealData1(String data) {
        this.data = data;
    }

    @Override
    public Object call() throws Exception {
        Thread.sleep(1000);
        return data;
    }
}