package com.wyx.future;

/**
 * Created by wangongxin on 17/8/2.
 */
public class FutureCilent {
    public Data getRequest(final String queryStr) {
        final FutureData futureData = new FutureData();
        new Thread(new Runnable() {
            @Override
            public void run() {
                RealData realData = new RealData(queryStr);
                futureData.setRealData(realData);
            }
        },"t1").start();
        return futureData;
    }
}
