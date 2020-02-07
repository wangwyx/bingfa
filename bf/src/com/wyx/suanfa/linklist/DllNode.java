package com.wyx.suanfa.linklist;

/**
 * @auth: wangyx-p
 * @date :2019/12/20 10:59
 * @Description:双向链表
 */
public class DllNode {
    private int data;
    private DllNode next;
    private DllNode previous;

    public DllNode(int data) {
        this.data = data;
    }

    public int getData() {
        return data;
    }

    public void setData(int data) {
        this.data = data;
    }

    public DllNode getNext() {
        return next;
    }

    public void setNext(DllNode next) {
        this.next = next;
    }

    public DllNode getPrevious() {
        return previous;
    }

    public void setPrevious(DllNode previous) {
        this.previous = previous;
    }
    public int getDllLength(DllNode headNode) {
        int length = 0;
        DllNode currentNode = headNode;
        while (null != currentNode) {
            length++;
            currentNode = currentNode.getNext();
        }
        return length;
    }
    DllNode DllInsert(DllNode headNode, DllNode nodeToInsert, int position) {

        if (null == headNode) {//最初为空时插入
            return nodeToInsert;
        }
        int size = getDllLength(headNode);
        if (position > size + 1 || position < 1) {
            System.out.println("Position of nodeToInsert is invalid. The valid inputs are 1to  " + (size + 1));
            return headNode;
        }
        if (position == 1) {//链表开头插入
            nodeToInsert.setNext(headNode);
            headNode.setPrevious(nodeToInsert);
            return nodeToInsert;
        }else{//中间或末尾
            DllNode previousNode = headNode;
            int count = 1;
            while (count < position - 1) {
                previousNode = previousNode.getNext();
                count++;
            }
            DllNode currentNode = previousNode.getNext();
            nodeToInsert.setNext(currentNode);
            if (currentNode != null) {
                currentNode.setPrevious(nodeToInsert);
            }
            previousNode.setNext(nodeToInsert);
            nodeToInsert.setPrevious(previousNode);
        }
        return headNode;
    }


    DllNode DllDelete(DllNode headNode, int position) {
        int size = getDllLength(headNode);
        //
        if (position > size || position < 1) {
            System.out.println("Position of nodeToInsert is invalid. The valid inputs are 1to  " + (size + 1));
            return headNode;
        }

        if (position == 1) {//删除链表中的第一个节点
            DllNode currentNode = headNode.getNext();
            headNode = null;
            currentNode.setPrevious(null);
            return currentNode;
        }else{//删除中间或者尾部节点
            DllNode previousNode = headNode;
            int count = 1;
            while (count < position - 1) {
                previousNode = previous.getNext();
                count++;
            }
            DllNode currentNode = previousNode.getNext();
            DllNode laterNode = currentNode.getNext();
            previousNode.setNext(laterNode);
            if (laterNode != null) {
                //如果被删除节点的后继节点不是null节点，那么设置其前驱
                //指针指向被删除节点的前驱节点
                laterNode.setNext(previousNode);
            }
            currentNode = null;
        }
        return headNode;
    }
    public static void main(String[] args) {
        DllNode node = new DllNode(1);
        DllNode node0 = new DllNode(0);
        DllNode node1 = new DllNode(0);
        DllNode nodeInsert = new DllNode(0);

        node.setPrevious(node0);
        node.setNext(node1);
        System.out.println(node.getDllLength(node));
        node.DllInsert(node, nodeInsert, 1);
    }
}
