package com.algo.array;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * ˳ʱ����������е�Ԫ��
 * https://mp.weixin.qq.com/s?__biz=MzIxMjE5MTE1Nw==&mid=2653212352&idx=1&sn=045f46d5d35ff1d9e88442d4e11dbe76&chksm=8c99bc1abbee350c4e371a6f84245029be0c19b716a0d5a59b7e787b23a062508efc15a259d9&mpshare=1&scene=24&srcid=0921bMbYL85tu3XB9nuohuO2&sharer_sharetime=1600668282267&sharer_shareid=933be855255b9ebb501cdc6459a0fb73#rd
 */
public class SpiralOrder {

    public static List<Integer> spiralOrder(int[][] matrix) {
        List<Integer> list = new ArrayList<Integer>();
        //����ά�����ǿջ��κ�һ��ά����0��ֱ�ӷ���
        if (matrix == null || matrix.length == 0 || matrix[0].length == 0) {
            return list;
        }
        //m�Ǿ��������
        int m = matrix.length;
        //n�Ǿ��������
        int n = matrix[0].length;
        //��ѭ����������������������
        for(int i=0; i<(Math.min(m, n)+1)/2; i++) {
            //�����ұ������ϱߡ�
            for (int j=i; j<n-i; j++) {
                list.add(matrix[i][j]);
            }
            //���ϵ��±������ұߡ�
            for (int j=i+1; j<m-i; j++) {
                list.add(matrix[j][(n-1)-i]);
            }
            //���ҵ���������±ߡ�
            for (int j=i+1; j<n-i; j++) {
                list.add(matrix[(m-1)-i][(n-1)-j]);
            }
            //���µ��ϱ�������ߡ�
            for (int j=i+1; j<m-1-i; j++) {
                list.add(matrix[(m-1)-j][i]);
            }
        }
        return list;
    }

    public static void main(String[] args) {
        int[][] matrix = {
                { 1,  2,  3,  4,  5  },
                { 6,  7,  8,  9,  10 },
                { 11, 12, 13, 14, 15 },
                { 16, 17, 18, 19, 20 }
        };
        int[][] matrix2 = {
                { 1,  2,  3,  4,  5  },
                { 6,  7,  8,  9,  10 },
                { 11, 12, 13, 14, 15 },
                { 16, 17, 18, 19, 20 },
                { 21, 22, 23, 24, 25 }
        };
        List<Integer> resultList1 = spiralOrder(matrix);
        System.out.println(Arrays.toString(resultList1.toArray()));
        List<Integer> resultList2 = spiralOrder(matrix2);
        System.out.println(Arrays.toString(resultList2.toArray()));
    }
}