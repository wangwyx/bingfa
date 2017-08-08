package com.wyx.future;

/**
 * Created by wangongxin on 17/8/2.
 */
public class FutureData implements Data{
    private RealData realData;
    private boolean isReady = false;

    public synchronized void setRealData(RealData realData) {
        //
        if (isReady) {
            return;
        }
        this.realData = realData;
        isReady = true;
        //进行通知
        notify();
    }
    @Override
    public synchronized String getRequest() {
        while (!isReady) {
            try {
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        return  this.realData.getRequest() ;
    }
}
