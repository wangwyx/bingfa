package com.wyx.first;

/**
 * Created by Administrator on 2016/11/8.
 */
public class PrimeGenerator extends Thread {
    @Override
    public void run() {
        long number = 1L;
        while (true) {
            if (isPrime(number)) {
                System.out.printf("Number %d is Prime\n", number);
            }
            if (Thread.interrupted()) {
                System.out.printf("The Prime Generator has been Interrupted");
                return;
            }
            number++;
        }
    }

    /**
     * 数字是否是质数
     * @param number
     * @return
     */
    private boolean isPrime(long number) {
        if (number <=2) {
            return true;
        }

        for (long i=2; i<number; i++){
            if ((number % i)==0) {
                return false;
            }
        }
        return true;
    }
}
