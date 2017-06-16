package com.wyx.first;

/**
 * Created by Administrator on 2016/11/8.
 */
public class testPrimeGenerator {
    public static void main(String[] args) {
        Thread task=new PrimeGenerator();
        task.start();
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        task.interrupt();
    }
}
