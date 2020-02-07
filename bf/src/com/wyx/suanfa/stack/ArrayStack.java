package com.wyx.suanfa.stack;

/**
 * @auth: wangyx-p
 * @date :2019/12/26 16:47
 * @Description:基于静态数组实现的栈
 */
public class ArrayStack {

    private int top;//栈顶
    private int capacity;
    private int[] array;

    public ArrayStack() {
        capacity = 1;
        array = new int[capacity];
        top = -1;
    }

    public boolean isEmpty(){
        return top == -1;
    }

    public boolean isStackFull() {
        return top == capacity;
    }

    public void push(int data) {
        if (isStackFull()) {
            System.out.println("Stack Overflow");
        }else{
            array[++top] = data;
        }
    }

    public int pop(){
        if (isEmpty()) {
            System.out.println("stack empty");
            return 0;
        } else {
            return array[top--];
        }
    }

    public void deleteStack(){
        top = -1;
    }
}
