package com.wyx.suanfa.sorts;

import java.util.Arrays;

/**
 * @auth: wangyx-p
 * @date :2019/12/6 17:25
 * @Description:冒泡排序
 * 冒泡排序只会操作相邻的两个数据。每次冒泡操作都会对相邻的两个元素进行比较，看是否满足大小关系要求
 */
public class BubbleSort {

    // 冒泡排序，a表示数组，n表示数组大小
    public void bubbleSort(int[] a, int n) {
        if (n <= 1) return;

        for (int i = 0; i < n; ++i) {
            // 提前退出冒泡循环的标志位
            boolean flag = false;
            for (int j = 0; j < n - i - 1; ++j) {
                if (a[j] > a[j+1]) { // 交换
                    int tmp = a[j];
                    a[j] = a[j+1];
                    a[j+1] = tmp;
                    flag = true;  // 表示有数据交换
                }
            }
            if (!flag) break;  // 没有数据交换，提前退出
        }
    }

    /**
     * 使用双循环来进行排序。外部循环控制所有的回合，内部循环代表每一轮的冒泡处理，先进行元素比较，再进行元素交换。
     * @param array
     */
    private static void sort(int array[]){
        int tmp  = 0;
        for(int i = 0; i < array.length; i++){
            for(int j = 0; j < array.length - i - 1; j++){
                if(array[j] > array[j+1]){
                    tmp = array[j];
                    array[j] = array[j+1];
                    array[j+1] = tmp;
                }
            }
        }
    }

    /**
     * 利用布尔变量isSorted作为标记。如果在本轮排序中，元素有交换，则说明数列无序；如果没有元素交换，说明数列已然有序，直接跳出大循环。
     * @param array
     */
    private static void sort2(int array[]){
        int tmp  = 0;
        for(int i = 0; i < array.length; i++){
            //有序标记，每一轮的初始是true
            boolean isSorted = true;
            for(int j = 0; j < array.length - i - 1; j++){
                if(array[j] > array[j+1])
                {
                    tmp = array[j];
                    array[j] = array[j+1];
                    array[j+1] = tmp;
                    //有元素交换，所以不是有序，标记变为false
                    isSorted = false;
                }
            }
            if(isSorted){
                break;
            }
        }
    }


    /**
     * 第二轮结束，数列有序区包含一个元素：213456789,右边的很多元素都还是有序的了，每一轮循环都白白匹配很多次
     *
     * 解决问题的关键在于对数列有序区的界定。
     *
     *
     * 按照现有的逻辑，有序区的长度和排序的轮数是相等的。比如第一轮排序过后的有序区长度是1，第二轮排序过后的有序区长度是2 ......
     *
     *
     * 实际上，数列真正的有序区可能会大于这个长度，比如例子中仅仅第二轮，后面5个元素实际都已经属于有序区。因此后面的许多次元素比较是没有意义的。
     *
     *
     * 如何避免这种情况呢？我们可以在每一轮排序的最后，记录下最后一次元素交换的位置，那个位置也就是无序数列的边界，再往后就是有序区了。
     * @param array
     */
    private static void sort3(int array[]){
        int tmp  = 0;
        //记录最后一次交换的位置
        int lastExchangeIndex = 0;
        //无序数列的边界，每次比较只需要比到这里为止
        int sortBorder = array.length - 1;
        for(int i = 0; i < array.length; i++){
            //有序标记，每一轮的初始是true
            boolean isSorted = true;
            for(int j = 0; j < sortBorder; j++){
                if(array[j] > array[j+1]){
                    tmp = array[j];
                    array[j] = array[j+1];
                    array[j+1] = tmp;
                    //有元素交换，所以不是有序，标记变为false
                    isSorted = false;
                    //把无序数列的边界更新为最后一次交换元素的位置
                    lastExchangeIndex = j;
                }
            }
            sortBorder = lastExchangeIndex;
            if(isSorted){
                break;
            }
        }

    }



    /**
     *  从前往后冒泡
     *  上面的图片就是采用这种方式
     */
    public void bubbleSort1(int[] arr, int n) {
        for (int i = 0; i < n; i++) {
            // flag是用来标记本次冒泡中是否有元素交换，用来决定冒泡停止条件的
            boolean flag = false;
            for (int j = 0; j < n-i-1; j++) {
                // 从第一个开始，相邻元素两两比较，如果前一个比后一个大则交换
                if (arr[j] < arr[j+1]) {
                    int temp = arr[j];
                    arr[j] = arr[j+1];
                    arr[j+1] = temp;
                    flag = true; // 如果有元素交换了，就设置为true
                }
            }
            // 一次冒泡下来没有元素交换，就提前退出
            if (!flag) break;
        }
    }

    /**
     *  从后往前冒泡
     */
    public void bubbleSort2(int[] arr, int n) {
        for (int i = 0; i < n; i++) {
            // flag是用来标记本次冒泡中是否有元素交换，用来决定冒泡停止条件的
            boolean flag = false;
            for (int j = n-i-1; j < i; j--) {
                // 从第最后一个开始，相邻元素两两比较，如果前一个比后一个大则交换
                if (arr[j-1] < arr[j]) {
                    int temp = arr[j-1];
                    arr[j-1] = arr[j];
                    arr[j] = temp;
                    flag = true; // 如果有元素交换了，就设置为true
                }
            }
            // 一次冒泡下来没有元素交换，就提前退出
            if (!flag) break;
        }
    }

    public static void main(String[] args){
        int[] array = new int[]{5,8,6,3,9,2,1,7};
        sort(array);
        System.out.println(Arrays.toString(array));
    }
}
