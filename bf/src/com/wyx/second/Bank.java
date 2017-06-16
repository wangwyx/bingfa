package com.wyx.second;

/**
 * Created by Administrator on 2016/11/10.
 */
public class Bank implements Runnable{
    private Account account;
    @Override
    public void run() {
        for (int i=0; i<100; i++){
            account.subtractAmount(1000);
        }
    }

    public Bank(Account account) {
        this.account = account;
    }
}
