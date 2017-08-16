package com.wyx.lock;

import java.util.concurrent.Exchanger;

/**Exchanger交互器，它是一种比较特殊的两方(Two-Party)栅栏，可以理解成Exchanger是一个栅栏，两边一方是生产者，一方是消费者，
 1. 生产者和消费者各自维护了一个容器，生产者往容器里生产东西，消费者从容器里消费东西。
 2. 当生产者的容器是满的时候，它需要通过Exchanger向消费者交换，把满的容器交换给消费者，从消费者手里拿到空的容器继续生产。
 3. 当消费者的容器是空的时候，它需要通过Exchanger向生产者交换，把空的容器交换给生产者，从生产者手里拿到满的容器继续消费。
 所以我们看到这个过程中至少有5个组件
 1. Exchanger栅栏
 2. 生产者
 3. 消费者
 4. 生产者的容器
 5. 消费者的容器
 * Created by Administrator on 2017/8/16.
 */
public class ExchangerUsecase {
    private static Exchanger<Buffer<Integer>> exchanger = new Exchanger<Buffer<Integer>>();
    private static Buffer<Integer> emptyBuffer = new Buffer<Integer>();
    private static Buffer<Integer> fullBuffer = new Buffer<Integer>();

    private static class Buffer<T>{
        private T[] cache = (T[])(new Object[2]);
        private int index = 0;

        public void add(T item){
            cache[index++] = item;
        }

        public T take(){
            return cache[--index];
        }

        public boolean isEmpty(){
            return index == 0;
        }

        public boolean isFull(){
            return index == cache.length;
        }
    }

    public static void main(String[] args) {
        Runnable provider = new Runnable(){
            Buffer<Integer> currentBuffer = emptyBuffer;
            private int exchangeCount = 0;
            @Override
            public void run() {
                while(currentBuffer != null && exchangeCount <= 1){
                    if(!currentBuffer.isFull()){
                        System.out.println("Provider added one item");
                        currentBuffer.add(1);
                    }else{
                        try {
                            currentBuffer = exchanger.exchange(currentBuffer);
                            exchangeCount ++;
                            Thread.sleep(2000);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                }

            }

        };

        Runnable consumer = new Runnable(){
            Buffer<Integer> currentBuffer = fullBuffer;
            private int exchangeCount = 0;
            @Override
            public void run() {
                while(currentBuffer != null  && exchangeCount <= 2){
                    if(!currentBuffer.isEmpty()){
                        System.out.println("Consumer took one item");
                        currentBuffer.take();
                    }else{
                        try {
                            currentBuffer = exchanger.exchange(currentBuffer);
                            exchangeCount ++;
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                }

            }

        };

        new Thread(provider).start();
        new Thread(consumer).start();
    }

}
