package com.wyx.ThreadPool;

import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Created by wangongxin on 17/7/9.
 * 都和ThreadPoolExecutor有关
 */
public class UseExecutor {
    public static void main(String[] args) {
        ExecutorService poll1 = Executors.newFixedThreadPool(10);
        ExecutorService poll2 = Executors.newSingleThreadExecutor();
        ExecutorService poll3 = Executors.newSingleThreadExecutor();
        ExecutorService poll4 = Executors.newCachedThreadPool();
    }
}
