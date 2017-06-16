package com.wyx.second;

/**
 * Created by Administrator on 2016/11/10.
 */
public class Account {
    private double balance;

    public double getBalance() {
        return balance;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }

    public synchronized void addAmount(double amout) {
        double tmp = amout;
        try {
            Thread.sleep(10);
        } catch (Exception e) {

        }
        tmp+= amout;
        balance = tmp;
    }
    public synchronized void subtractAmount(double amount) {
        double tmp=balance;
        try {
            Thread.sleep(10);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        tmp-=amount;
        balance=tmp;
    }
}
