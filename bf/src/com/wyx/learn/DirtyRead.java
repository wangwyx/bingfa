package com.wyx.learn;

/**
 * Created by Administrator on 2017/6/16.
 */
public class DirtyRead {
    private String username ="ssdf";
    private String password = "sdfas";

    public synchronized void setValue(String username, String password) {
        this.username = username;
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        this.password = password;
        System.out.println("last result:username="+username+",password="+password);
    }

    public void getValue() {
        System.out.println("getValue:username="+this.username+",password="+this.password);
    }

    public static void main(String[] args) throws InterruptedException {
        final DirtyRead d1 = new DirtyRead();
        Thread t1 = new Thread(new Runnable() {
            @Override
            public void run() {
                d1.setValue("sdf","dsfa");
            }
        },"t1");
        t1.start();
        Thread.sleep(1000);
        d1.getValue();
    }
}
