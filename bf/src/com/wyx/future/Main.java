package com.wyx.future;

/**
 * Created by wangongxin on 17/8/2.
 */
public class Main {
    public static void main(String[] args) {
        FutureCilent fc = new FutureCilent();
        Data data = fc.getRequest("请求参数");
        System.out.println();
        System.out.println();
        String result = data.getRequest();
        System.out.println(result);
    }
}
