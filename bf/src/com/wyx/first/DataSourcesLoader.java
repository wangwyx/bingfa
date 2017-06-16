package com.wyx.first;

import java.util.Date;
import java.util.concurrent.TimeUnit;

/**
 * Created by Administrator on 2016/11/9.
 */
public class DataSourcesLoader implements Runnable {
    @Override
    public void run() {
        System.out.printf("DataSourcesLoader:Beginning data sources loading: %s\n",new Date());
        try {
            TimeUnit.SECONDS.sleep(8);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.printf("DataSourcesLoader:Data sources loading has finished:%s\n",new Date());
    }
}
