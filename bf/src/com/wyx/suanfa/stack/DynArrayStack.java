package com.wyx.suanfa.stack;

/**
 * @auth: wangyx-p
 * @date :2019/12/26 17:09
 * @Description:
 */
public class DynArrayStack {
    private int top;//栈顶
    private int capacity;
    private int[] array;
    // 初始化数组，申请一个大小为 n 的数组空间
    public DynArrayStack() {
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
            doubleStack();
        }else{
            array[++top] = data;
        }
    }

    public void doubleStack(){
        int newArray[] = new int[capacity * 2];
        System.arraycopy(array, 0, newArray, 0, capacity);
        capacity = capacity * 2;
        array = newArray;
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
