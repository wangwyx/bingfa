package com.wyx.first;

import java.util.Date;

/**
 * Created by Administrator on 2016/11/9.
 */
public class TestJoin {
    public static void main(String[] args) {
        DataSourcesLoader dsLoader = new DataSourcesLoader();
        Thread thread1 = new Thread(dsLoader,"DataSourceThread");

        NetworkConnectionsLoader ncLoader = new NetworkConnectionsLoader();
        Thread thread2 = new Thread(ncLoader,"NetworkConnectionLoad er");
        thread1.start();
        thread2.start();
        try {
            thread2.join();
            thread1.join();

        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.printf("Main: Configuration has been loaded: %s\n",new Date());
    }
}
