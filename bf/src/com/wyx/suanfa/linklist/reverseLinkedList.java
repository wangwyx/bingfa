package com.wyx.suanfa.linklist;

/**
 * @auth: wangyx-p
 * @date :2019/12/26 16:30
 * @Description:单向链表逆序
 * 可查看https://mp.weixin.qq.com/s?__biz=MzI2NjA3NTc4Ng==&mid=2652080797&idx=1&sn=c5e483c044a714a5907f477a08306e5c&chksm=f1748178c603086eb35291154644a39ed631294c38f238241617df77f82348203f23859b4c3b&mpshare=1&scene=24&srcid=#rd
 */
public class reverseLinkedList {

    private static Node head;

    public static void reverseLinkedList(){
        if(head==null || head.next==null){
            return;
        }

        Node p1 = head;
        Node p2 = head.next;
        Node p3 = null;

        while (p2!=null){
            p3 = p2.next;
            p2.next = p1;
            p1 = p2;
            p2 = p3;
        }

        head.next = null;
        head = p1;
    }



    private static class Node {
        int data;
        Node next;

        Node(int data) {
            this.data = data;
        }
    }



    public static void main(String[] args){
        //初始化链表
        head = new Node(3);
        head.next = new Node(5);
        Node temp = head.next;
        temp.next = new Node(1);
        temp = temp.next;
        temp.next = new Node(4);
        temp = temp.next;
        temp.next = new Node(9);

        //逆序前输出链表
        temp = head;
        while(temp!=null){
            System.out.println(temp.data);
            temp = temp.next;
        }

        //逆序链表
        reverseLinkedList();
        //逆序后输出链表
        temp = head;
        while(temp!=null){
            System.out.println(temp.data);
            temp = temp.next;
        }
    }
}
