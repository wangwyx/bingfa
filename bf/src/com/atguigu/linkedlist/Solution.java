package com.atguigu.linkedlist;

import java.util.ArrayList;
import java.util.List;

/**
 * ��ת��������
 */
public class Solution {

    public static class Node {
        public int data;// �ڵ�Ķ��󣬼�����

        // �ڵ�����ã�ָ����һ���ڵ�,ע�⣺���Բ��ܶ���Ϊpublic,����ֻ��Ϊ����ʾ����,�����л���Ҫ����� private!
        public Node next = null;

        public Node(int val) {
            data = val;
        }

    }

    /**
     * ����
     */
    public static class LinkedList {
        int length = 0; // �����ȣ��Ǳ��룬�ɲ���
        Node head = new Node(0); // �ڱ��ڵ�

        /**
         * β�巨��������
         * @param val
         */
        public void addNode(int val) {
            Node tmp = head;
            while (tmp.next != null) {
                tmp = tmp.next;
            }
            tmp.next = new Node(val);
        }

        /**
         * ͷ�巨��������
         * @param val
         */
        public void headInsert(int val) {
            // �����½��
            Node newNode = new Node(val);

            // �½��ָ��ͷ���֮��Ľ��
            newNode.next = head.next;

            // ͷ���ָ���½��
            head.next = newNode;
        }

        /**
         * ɾ��ָ���Ľ��
         * @param deletedNode
         */
        public void removeSelectedNode(Node deletedNode) {
            // ����˽����β������ǻ���Ҫ��ͷ������β����ǰ�̽�㣬�ٽ�β���ɾ��
            if (deletedNode.next == null) {
                Node tmp = head;
                while (tmp.next != deletedNode) {
                    tmp = tmp.next;
                }
                // �ҵ�β����ǰ�̽�㣬��β���ɾ��
                tmp.next = null;
            } else {
                Node nextNode = deletedNode.next;
                // ��ɾ�����ĺ�̽���ֵ������ɾ�����
                deletedNode.data = nextNode.data;;
                // �� nextNode ���ɾ��
                deletedNode.next = nextNode.next;;
                nextNode.next = null;
            }
        }

        /**
         * �ݹ鷭ת��� node ��ʼ������
         * @param node
         * @return
         */
        public Node invertLinkedList(Node node) {
            if (node.next == null) {
                return node;
            }

            // ���� 1: �ȷ�ת node ֮�������
            Node newHead = invertLinkedList(node.next);

            // ���� 2: �ٰ� node �ڵ��̽��ĺ�̽��(3)ָ�� node��node �ĺ�̽ڵ�����Ϊ��
            node.next.next = node;
            node.next = null;

            // ���� 3: ���ط�ת���ͷ���
            return newHead;
        }

        /**
         * ������ת
         */
        public void iterationInvertLinkedList() {
            // ���� 1
            Node pre = head.next;
            Node cur = pre.next;
            pre.next = null;

            while (cur != null) {
                /**
                 * ���ע�⣺�� cur ָ�� pre ֮ǰһ��Ҫ�ȱ��� cur �ĺ�̽�㣬��Ȼ cur ָ�� pre �����Ҳ�Ҳ�����̽����
                 * Ҳ���޷��� cur ���֮��Ľ����з�ת��
                 */
                Node next = cur.next;
                cur.next = pre;
                pre = cur;
                cur = next;
            }
            // ��ʱ pre Ϊͷ���ĺ�̽��
            head.next = pre;
        }

        /**
         * ������ת from �� to �Ľ��
         */
        public void iterationInvertLinkedList(int fromIndex, int toIndex) throws Exception {
            // ���� 1
            Node fromPre = null;            // from-1���
            Node from = null;               // from ���
            Node to = null;                 // to ���
            Node toNext = null;             // to+1 ���

            Node tmp = head.next;
            int curIndex = 1;      // ͷ����indexΪ1
            while (tmp != null) {
                if (curIndex == fromIndex-1) {
                    fromPre = tmp;
                } else if (curIndex == fromIndex) {
                    from = tmp;
                } else if (curIndex == toIndex) {
                    to = tmp;
                } else if (curIndex == toIndex+1) {
                    toNext = tmp;
                }
                tmp = tmp.next;
                curIndex++;
            }

            if (from == null || to == null) {
                // from �� to ������β��㲻��ת
                throw new Exception("����������");
            }

            // ����ʹ��ѭ����������ת�� from �� to �Ľ��
            Node pre = from;
            Node cur = pre.next;
            while (cur != toNext) {
                Node next = cur.next;
                cur.next = pre;
                pre = cur;
                cur = next;
            }

            if (fromPre != null) {
                fromPre.next = to;
            } else {
                // ��� fromPre Ϊ�գ�˵���Ǵ�head �ĺ�̽ڵ㿪ʼ��ת��
                head.next = to;
            }
            from.next = toNext;
        }

        /**
         * ˳��ÿ k ��һ�鷭ת����
         * @param k
         */
        public void iterationInvertLinkedListEveryK(int k) {
            Node tmp = head.next;
            int step = 0;               // �����������ҳ��׽���β���

            Node startK = null;         // k��һ�������е�ͷ���
            Node startKPre = head;      // k��һ���������ͷ����ǰ�ý��
            Node endK;                  // k��һ�������е�β���
            while (tmp != null) {
                // ��ǰ���� tmp ����һ���ڵ�,��Ϊ���ڷ�ת��tmp �ĺ�̽����
                Node tmpNext = tmp.next;
                if (step == 0) {
                    // k ��һ�����������ͷ���
                    startK = tmp;
                    step++;
                } else if (step == k-1) {
                    // ��ʱ�ҵ��� k ��һ�����������β��㣨endK��,����������õ������з�ת
                    endK = tmp;
                    Node pre = startK;
                    Node cur = startK.next;
                    if (cur == null) {
                        break;
                    }
                    Node endKNext = endK.next;
                    while (cur != endKNext) {
                        Node next = cur.next;
                        cur.next = pre;
                        pre = cur;
                        cur = next;
                    }
                    // ��ת���ʱ endK �� startK �ֱ����� k ��һ�������е���β���
                    startKPre.next = endK;
                    startK.next = endKNext;

                    // ��ǰ�� k ��һ�鷭ת���ˣ���ʼ��һ�� k ��һ��ķ�ת
                    startKPre = startK;
                    step = 0;
                } else {
                    step++;
                }
                tmp = tmpNext;
            }
        }

        /**
         * ����ÿ k ��һ�鷭ת����
         * @param k
         */
        public void reverseIterationInvertLinkedListEveryK(int k) {
            // �ȷ�ת����
            iterationInvertLinkedList();
            // k ��һ�鷭ת����
            iterationInvertLinkedListEveryK(k);
            // �ٴη�ת����
            iterationInvertLinkedList();
        }

        /**
         * ��ӡ����
         */
        public void printList() {
            Node tmp = head.next;
            List<Integer> arr = new ArrayList<>();
            while (tmp != null) {
                arr.add(tmp.data);
                tmp = tmp.next;
            }
            int length = arr.size();
            for (int i = 0; i < length; i++) {
                if (i != arr.size() -1) {
                    System.out.print(arr.get(i) + "--->");
                } else {
                    System.out.print(arr.get(i));
                }
            }

            System.out.println("\n");
        }
    }

    public static void main(String[] args) throws Exception {
        LinkedList linkedList = new LinkedList();
        int[] arr = {1,2,3,4,5};
        for (int i = 0; i < arr.length; i++) {
            linkedList.addNode(arr[i]);
        }

        // ͷ�巨��������
        // for (int i = 0; i < arr.length; i++) {
        //      linkedList.headInsert(arr[i]);
        // }

        // ɾ��ָ����㣨����ָ��ֵΪ 2 �Ľ�㣩
        // Node tmp = linkedList.head;
        // while (tmp.data != 2) {
        //     tmp = tmp.next;
        // }
        // linkedList.removeSelectedNode(tmp);

        // �ݹ鷭ת
          Node newHead = linkedList.invertLinkedList(linkedList.head.next);
         linkedList.head.next = newHead;
         linkedList.printList();

        // ������ת
        // linkedList.iterationInvertLinkedList();
        // linkedList.printList();

        // ��ת������ӽ�� from ����� to
        // linkedList.iterationInvertLinkedList(2, 3);
        // linkedList.printList();

        // ÿ k ��һ�鷭ת����
        // linkedList.iterationInvertLinkedListEveryK(1);
        // linkedList.printList();

        // ����ÿ k ��һ�鷭ת����
//        linkedList.reverseIterationInvertLinkedListEveryK(3);
//        linkedList.printList();
    }
}