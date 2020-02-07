package com.wyx.suanfa.sorts;

import java.util.Arrays;

/**
 * 插入排序（插入位置，从头至尾搜索）
 * @Author： ooooor
 * 可查看博客介绍：https://mp.weixin.qq.com/s?__biz=MzIxMjE5MTE1Nw==&mid=2653199343&idx=1&sn=a5491fa908e45e6117423d9ba5062611&chksm=8c99e935bbee60232aacb7c2b74961a24e7b86d44bf98357c597ad277a8eb15639c1de7034d9&mpshare=1&scene=24&srcid=&sharer_sharetime=1565588012337&sharer_shareid=933be855255b9ebb501cdc6459a0fb73#rd
最好时间复杂度为O(n)
平均时间复杂度为 O(n²)
 */
public class InsertionSortAdd {

    public static void main(String[] args) {
        int[] data = new int[]{4, 6, 5, 3, 7, 1, 2};
        fromStartToEnd(Arrays.copyOf(data, data.length));
        System.out.println(Arrays.toString(data));

        int array[]={12,1,3,46,5,0,-3,12,35,16};

        sort(array);

        System.out.println(Arrays.toString(array));
    }

    /**
     * 查询插入位置时， 从头至尾搜索
     * @param data
     */
    private static void fromStartToEnd(int[] data) {
        for (int i=1; i < data.length; i++) {
            int value = data[i];
            int[] tmp = new int[2];
            int change = i;
            for (int j=0; j < i; j++) {
                if(value >= data[j]) {
                    continue;
                }
                int index = j%2;
                if (change == i) {
                    tmp[Math.abs(index-1)] = data[j];
                    change = j;
                }
                tmp[index] = data[j+1];
                if (0 == index) {
                    data[j+1] = tmp[index+1];
                } else {
                    data[j+1] = tmp[index-1];
                }
            }
            data[change] = value;
        }
    }

    public static void sort(int[] array){
        for(int i=1;i<array.length;i++){
            int insertValue =array[i];
            int j=i-1;
            //从右向左比较元素的同时，进行元素复制
            for(; j>=0&&insertValue<array[j]; j--){
                array[j+1]=array[j];
            }
            //insertValue的值插入适当位置
            array[j+1]=insertValue;
        }
    }

    //算法题：对数组arr进行从小到大的排序，假设数组arr不为空，arr的长度为n
    //思路：采用插入排序方法

    public void inertSort(int[] arr){
        int i,j;
        int n = arr.length;

        for(i=1; i<n; i++){
            //位置0是“已经排序”区段，因此从位置1开始，依次取出“未排序”区段的元素
            int needSort = arr[i];
            //在“已经排序”区段中，从后往前循环，对比needSort
            for(j=i-1; j>=0; j--){
                //如果needSort小于arr[j]，则说明需要将arr[j]往后移动一位，以便留出空位
                if(needSort < arr[j]){
                    arr[j+1] = arr[j];
                }else{
                    break;
                }
            }
            //将元素放入到正确的位置
            arr[j+1] = needSort;
        }
    }

}