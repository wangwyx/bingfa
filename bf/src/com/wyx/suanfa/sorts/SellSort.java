package com.wyx.suanfa.sorts;

import java.util.Arrays;

/**
 * @auth: wangyx-p
 * @date :2019/12/12 10:11
 * @Description:希尔排序,
 * 分组排序：就是让元素两两一组，同组两个元素之间的跨度，都是数组总长度的一半
 */
public class SellSort {
    public static void sort(int [] array){
        //希尔排序的增量
        int d=array.length;
        while(d>1) {
            //使用希尔增量的方式，即每次折半
            d=d/2;
            for(int x=0;x<d;x++) {
                for(int i=x+d;i<array.length;i=i+d) {
                    int temp=array[i];
                    int j;
                    for(j=i-d;j>=0&&array[j]>temp;j=j-d) {
                        array[j+d]=array[j];
                    }
                    array[j+d]=temp;
                }
            }
        }
    }

    public static void main(String [] args){
        int[] array = {5,3,9,12,6,1,7,2,4,11,8,10};
        sort(array);
        System.out.println(Arrays.toString(array));
    }
}
