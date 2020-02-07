package com.wyx.suanfa.queue;

import java.util.Stack;

/**
 * @auth: wangyx-p
 * @date :2019/12/26 18:04
 * @Description:用栈实现队列
 * https://mp.weixin.qq.com/s?__biz=MzIxMjE5MTE1Nw==&mid=2653195750&idx=1&sn=231558735ddf64a9c10ad721ad537ea2&chksm=8c99ff3cbbee762a37e74e42c585688bf6fbfe70422d723b29219d1d9bbdf524217c97e2228b&scene=0#rd
 */
public class StackQueue {
    private Stack<Integer> stackA = new Stack<Integer>();

    private Stack<Integer> stackB = new Stack<Integer>();


    /**
     * 入队操作
     * @param element  入队的元素
     */

    public void enQueue(int element) {
        stackA.push(element);
    }


    /**
     * 出队操作
     */

    public Integer deQueue() {
        if(stackB.isEmpty()){
            if(stackA.isEmpty()){
                return null;
            }
            transfer();
        }
        return stackB.pop();
    }


    /**
     * 栈A元素转移到栈B
     */
    private void transfer(){
        while (!stackA.isEmpty()){
            stackB.push(stackA.pop());
        }

    }


    public static void main(String[] args) throws Exception {

        StackQueue stackQueue = new StackQueue();
        stackQueue.enQueue(1);
        stackQueue.enQueue(2);
        stackQueue.enQueue(3);
        System.out.println(stackQueue.deQueue());
        System.out.println(stackQueue.deQueue());
        stackQueue.enQueue(4);
        System.out.println(stackQueue.deQueue());
        System.out.println(stackQueue.deQueue());
    }
}
