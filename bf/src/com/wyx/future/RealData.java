package com.wyx.future;

/**
 * Created by wangongxin on 17/8/2.
 */
public class RealData implements Data{
    private String result;
    public RealData(String queryStr) {
        System.out.println("执行操作，开始耗时");
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("操作完毕，返回");
        result="最终结果";
    }

    @Override
    public String getRequest() {
        return result;
    }
}
