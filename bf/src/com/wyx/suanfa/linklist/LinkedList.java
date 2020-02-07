package com.wyx.suanfa.linklist;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * @auth: wangyx-p
 * @date :2019/11/21 17:31
 * @Description:
 */
class Node1{
    int data;
    int np;
    String names;
    Node1 next;

    public Node1(int data,String names,int np){
        this.np=np;
        this.names=names;
        this.data=data;
        this.next=null;
    }
}

public class LinkedList {
    private Node1 first;
    private Node1 last;

    public boolean isEmpty(){
        return first == null;
    }

    public void print(){
        Node1 current = first;
        while (null != current) {
            System.out.println("[" + current.data + "  " + current.names + " " + current.np + "]");
            current = current.next;
        }
        System.out.println();
    }


    public void insert(int data,String names,int np){
        Node1 node1 = new Node1(data, names, np);
        if (this.isEmpty()) {
            first = node1;
            last = node1;
        }else {
            last.next = node1;
            last = node1;
        }
    }

    public void insertNode(Node1 ptr){
        Node1 tmp;
        Node1 newNode;
        if (this.isEmpty()) {
            first = ptr;
            last = ptr;
        } else {
            if (ptr.next == first) {
                ptr.next = first;
                first = ptr;
            } else if (ptr.next == null) {
                last.next = ptr;
                last = ptr;
            } else {
                newNode = first;
                tmp = first;
                while (ptr.next != newNode.next) {
                    tmp = newNode;
                    newNode = newNode.next;
                }
                tmp.next = ptr;
                ptr.next = newNode;
            }
        }

    }

    public void deleNode(Node1 delNode){
        Node1 newNode;
        Node1 tmp;
        if (this.first.data == delNode.data) {
            first = first.next;
        } else if (this.last.data == delNode.data) {
            newNode = first;
            while (newNode.next != last) newNode = newNode.next;
            newNode.next=last.next;
            last=newNode;
        } else {

            newNode=first;
            tmp=first;
            while (newNode.data != delNode.data) {
                tmp = newNode;
                newNode = newNode.next;
            }
            tmp.next = delNode.next;
        }
    }

//    int ListLength(){
//
//    }

    public static void main(String args[]) throws IOException{
        BufferedReader buf;
        buf=new BufferedReader(new InputStreamReader(System.in));
        int num;
        String name;
        int score;

        System.out.println("请输入5个学生数据： ");
        LinkedList list = new LinkedList();
//        Node1 node1 = new Node1(1,"a",1);
//        Node1 node2 = new Node1(1,"a",1);
//        Node1 node3 = new Node1(1,"a",1);
//        Node1 node4 = new Node1(1,"a",1);
//        Node1 node5 = new Node1(1,"a",1);
//        Node1 node6 = new Node1(1,"a",1);
        list.insert(1,"asdf",12);
        list.insert(4, "sdf", 32);
        list.insert(2,"gaerew",22);
        list.insertNode(new Node1(4,"123",32));
        list.print();
//        list.deleNode(new Node1(3,"sdf",32));
//        list.print();
    }
}
