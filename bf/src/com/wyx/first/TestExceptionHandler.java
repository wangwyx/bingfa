package com.wyx.first;

/**
 * Created by Administrator on 2016/11/10.
 */
public class TestExceptionHandler {
    public static void main(String[] args) {
        Task task=new Task();
        Thread thread=new Thread(task);
        thread.setUncaughtExceptionHandler(new ExceptionHandler());
        thread.start();
    }
}
