package com.wyx.suanfa.linklist;

/**
 * @auth: wangyx-p
 * @date :2019/12/19 10:54
 * @Description:
 */
public class ListNode1 {
    private int data;
    private ListNode1 next;

    public ListNode1(int data, ListNode1 next) {
        this.data = data;
        this.next = next;
    }

    public int getData() {
        return data;
    }

    public void setData(int data) {
        this.data = data;
    }

    public ListNode1 getNext() {
        return next;
    }

    public void setNext(ListNode1 next) {
        this.next = next;
    }

    public int ListLength(ListNode1 headNode) {
        int length = 0;
        ListNode1 currentNode = headNode;
        while (null != currentNode) {
            length++;
            currentNode = currentNode.getNext();
        }
        return length;
    }

    ListNode1 insertInLinkedList(ListNode1 headNode, ListNode1 nodeToInsert, int position) {
        if (headNode == null) {//链表为空，直接返回
            return nodeToInsert;
        }

        int size = ListLength(headNode);
        if (position > size + 1 || position < 1) {
            System.out.println("插入位置异常，位置应该在1和"+size+"之间");
        }
        if (position==1) {//在链表的开头插入
            nodeToInsert.setNext(headNode);
            return nodeToInsert;
        }else {
            //在链表的中间或者末尾插入
            ListNode1 previousNode = headNode;
            int count = 1;
            while (count < position - 1) {
                previousNode = previousNode.getNext();
                count++;
            }
            ListNode1 currentNode = previousNode.getNext();
            nodeToInsert.setNext(currentNode);
            previousNode.setNext(nodeToInsert);
        }
        return headNode;
    }

    ListNode1 deleteNodeFromLinkedList(ListNode1 headNode, int position) {
        int size = ListLength(headNode);
        if (position > size || position < 1) {
            System.out.println("Position of node to delete is invalid. The valid inputs are 1 to " + size);
            return headNode;
        }
        if (position == 1) {//删除单向节点的头节点
            ListNode1 currentNode = headNode.getNext();
            headNode = null;
            return currentNode;
        }else {//删除中间或尾部节点
            ListNode1 previousNode = headNode;
            int count = 1;
            while (count < position) {
                previousNode = previousNode.getNext();
                count++;
            }
            ListNode1 currentNode = previousNode.getNext();
            previousNode.setNext(currentNode.getNext());
            currentNode = null;
        }
        return headNode;
    }

    public void deleteLinkedList(ListNode1 head) {
        ListNode1 auxilaryNode, iterator = head;
        while (iterator != null) {
            auxilaryNode = iterator.getNext();
            iterator = null;//在java中，垃圾回收器将自动处理
            iterator = auxilaryNode;//在实际应用中不需要实现该内容
        }
    }
    public static void main(String[] args) {
        ListNode1 node1 = new ListNode1(1, new ListNode1(2, null));

        System.out.println(node1.ListLength(node1));
    }
}
