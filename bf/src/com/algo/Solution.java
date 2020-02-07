package com.algo;

import java.util.ArrayList;
import java.util.List;

/**
 * Ѱ�ҡ�ɾ���������еĵ�k���ڵ�
 * Ҫ֪��������м��㣬����������Ҫ֪������ĳ���
 * ʹ�ÿ���ָ�룬����һ������
 * 1�� ����ָ��ͬʱָ�� head �ĺ�̽��
 *
 * 2�� ��ָ����һ������ָ��������
 *
 * 3�� ���ϵ��ظ�����2��ʲôʱ��ͣ�����أ���ȡ��������ĳ�������������ż��
 *
 *     ���������Ϊ����,�� fast.next = null ʱ,slow Ϊ�м���
 *     ���������Ϊż��,�� fast = null ʱ,slow Ϊ�м���
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
            length++;
        }

        /**
         * ���ڱ�����ﶨ���������ȵ�����£��ҵ��м���
         * @return
         */
        public Node findMiddleNode() {
            Node tmp = head.next;
            int middleLength = length / 2;
            while (middleLength > 0) {
                tmp = tmp.next;
                middleLength--;
            }
            return tmp;
        }

        /**
         * ���ڱ������δ���������ȵ�����£��ȱ����ҵ�����ĳ��ȣ��ٱ��� ������/2 �ҵ��м���
         * @return
         */
        public Node findMiddleNodeWithoutHead() {
            Node tmp = head.next;
            int length = 1;
            // ѡ����һ���õ�������
            while (tmp.next != null) {
                tmp = tmp.next;
                length++;
            }

            // �ٱ���һ���õ������м���
            tmp = head.next;
            int middleLength = length / 2;
            while (middleLength > 0) {
                tmp = tmp.next;
                middleLength--;
            }
            return tmp;
        }

        /**
         * ʹ�ÿ���ָ������ҵ��м���
         *  * ʹ�ÿ���ָ�룬����һ������
         *  * 1�� ����ָ��ͬʱָ�� head �ĺ�̽��
         *  *
         *  * 2�� ��ָ����һ������ָ��������
         *  *
         *  * 3�� ���ϵ��ظ�����2��ʲôʱ��ͣ�����أ���ȡ��������ĳ�������������ż��
         *  *
         *  *     ���������Ϊ����,�� fast.next = null ʱ,slow Ϊ�м���
         *  *     ���������Ϊż��,�� fast = null ʱ,slow Ϊ�м���
         * @return
         */
        public Node findMiddleNodeWithSlowFastPointer() {

            Node slow = head.next;
            Node fast = head.next;

            while (fast != null && fast.next != null) {
                // ��ָ��������
                fast = fast.next.next;
                // ��ָ����һ��
                slow = slow.next;
            }

            return slow;
        }

        /**
         * ���ҵ���� k �����
         *     �����ÿ���ָ��ͬʱָ�� head �ĺ�̽��
         *     ��ָ����ǰ�� k- 1 ��,���ߵ��� k �����
         *     ����ָ��ͬʱ������һ���������ظ��˲��裬ֱ����ָ���ߵ�β���,��ʱ�� slow ��㼴Ϊ����Ҫ�ҵĵ���� k �����
         * @param k
         * @return
         * @throws Exception
         */
        public Node findKthToTail(int k) throws Exception {
            Node slow = head.next;
            Node fast = head.next;

            // ��ָ�����Ƶ���k�����
            int tmpK = k - 1;
            while (tmpK > 0 && fast != null) {
                fast = fast.next;
                tmpK--;
            }

            // �ٽ�������k����������
            if (fast == null) {
                throw new Exception("K��㲻�����쳣");
            }

            // slow �� fast ͬʱ�����ƣ�ֱ�� fast �ߵ�β���
            while (fast.next != null) {
                slow = slow.next;
                fast = fast.next;
            }
            return slow;
        }

        /**
         * ����������ת K ��λ��
         * ��ʵ�Ƕ������ K ��λ�õĵ�һ�����Σ���Ҫ˼·����
         *
         *     ���ҵ������� K+1 �����, �˽��ĺ�̽�㼴Ϊ������ K �����
         *     �������� K+1 ���ĵĺ�̽������Ϊ null
         *     �� head �ĺ�̽������Ϊ�������õĵ����� K ����㣬��ԭβ���ĺ�̽������Ϊԭ head �ĺ�̽��
         * @param k
         * @throws Exception
         */
        public void reversedKthToTail(int k) throws Exception {
            // ֱ�ӵ���ʵ�ֵ� Ѱ�ҵ���k�����ķ����������� k+1
            Node KPreNode = findKthToTail(k+1);
            Node kNode = KPreNode.next;
            Node headNext = head.next;

            KPreNode.next = null;

            head.next = kNode;

            // Ѱ��β���
            Node tmp = kNode;
            while (tmp.next != null) {
                tmp = tmp.next;
            }
            tmp.next = headNext;
        }

        /**
         * �ж����������Ƿ�����ͬ�Ľ��
         * @param list1
         * @param list2
         * @return
         */
        public static Node detectCommonNode(LinkedList list1, LinkedList list2) {
            int length1 = 0;        // ���� list1 �ĳ���
            int length2 = 0;        // ���� list2 �ĳ���

            Node p1 = list1.head;
            Node p2 = list2.head;

            while (p1.next != null) {
                length1++;
                p1 = p1.next;
            }

            while (p2.next != null) {
                length2++;
                p2 = p2.next;
            }

            p1 = list1.head;
            p2= list2.head;

            // p1 �� p2 ǰ�� |length1-length2| ��
            if (length1 >= length2) {
                int diffLength = length1-length2;
                while (diffLength > 0) {
                    p1 = p1.next;
                    diffLength--;
                }
            } else {
                int diffLength = length2-length1;
                while (diffLength > 0) {
                    p2 = p2.next;
                    diffLength--;
                }
            }
            // p1,p2�ֱ�����������߱����߱Ƚϣ������ȣ���Ϊ��һ���ཻ���
            while (p1 != null && p2.next != null) {
                p1 = p1.next;
                p2 = p2.next;
                if (p1.data == p2.data) {
                    // p1��p2 ��Ϊ�ཻ��㣬���� p1 �� p2
                    return p1;
                }
            }
            // û���ཻ��㣬���ؿ�ָ��
            return null;
        }

        /**
         * �ж��Ƿ��л�,���ؿ���ָ���������,���򷵻ؿ�ָ��
         * @return
         */
        public Node detectCrossNode() {
            Node slow = head;
            Node fast = head;

            while (fast != null && fast.next != null) {
                fast = fast.next.next;
                slow = slow.next;

                if (fast == null) {
                    return null;
                }

                if (slow.data == fast.data) {
                    return slow;
                }
            }
            return null;
        }


        /**
         * ��û�����ڽ��
         * @return
         */
        public Node getRingEntryNode() {
            // ��ȡ����ָ���������
            Node crossNode = detectCrossNode();

            // ���û�������㣬��û�л�
            if (crossNode == null) {
                return null;
            }

            // �ֱ�������ָ�룬һ��ָ��ͷ��㣬һ��ָ���ཻ���
            Node tmp1 = head;
            Node tmp2 = crossNode;

            // ���������㼴Ϊ������ڽ��
            while (tmp1.data != tmp2.data) {
                tmp1 = tmp1.next;
                tmp2 = tmp2.next;
            }

            return tmp1;
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
        LinkedList linkedList1 = new LinkedList();
        int[] arr1 = {1,2,3,4,5,6};


        for (int i = 0; i < arr1.length; i++) {
            linkedList1.addNode(arr1[i]);
        }

        Node middle = linkedList1.findKthToTail(4);
        System.out.println("middle = " + middle.data);

    }
}