package com.wyx.suanfa.sorts;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Stack;

/**
 * @auth: wangyx-p
 * @date :2019/12/12 10:51
 * @Description:快速排序，从冒泡排序演变来的，分治法
 * 可查看博客：
 * https://mp.weixin.qq.com/s?__biz=MzIxMjE5MTE1Nw==&mid=2653195042&idx=1&sn=2b0915cd2298be9f2163cc90a3d464da&chksm=8c99f9f8bbee70eef627d0f5e5b80a604221abb3a1b5617b397fa178582dcb063c9fb6f904b3&scene=0#rd
代码中，quickSort方法通过递归的方式，实现了分而治之的思想。
partition方法则实现元素的移动，让数列中的元素依据自身大小，分别移动到基准元素的左右两边。在这里，我们使用移动方式是挖坑法。
 */
public class QuickSort2 {
    public static void quickSort(int[] arr, int startIndex, int endIndex) {

        // 用一个集合栈来代替递归的函数栈

        Stack<Map<String, Integer>> quickSortStack = new Stack<Map<String, Integer>>();

        // 整个数列的起止下标，以哈希的形式入栈

        Map rootParam = new HashMap();

        rootParam.put("startIndex", startIndex);

        rootParam.put("endIndex", endIndex);

        quickSortStack.push(rootParam);

        // 循环结束条件：栈为空时结束

        while (!quickSortStack.isEmpty()) {

            // 栈顶元素出栈，得到起止下标

            Map<String, Integer> param = quickSortStack.pop();

            // 得到基准元素位置

            int pivotIndex = partition(arr, param.get("startIndex"), param.get("endIndex"));

            // 根据基准元素分成两部分, 把每一部分的起止下标入栈

            if(param.get("startIndex") <  pivotIndex -1){

                Map<String, Integer> leftParam = new HashMap<String, Integer>();

                leftParam.put("startIndex",  param.get("startIndex"));

                leftParam.put("endIndex", pivotIndex -1);

                quickSortStack.push(leftParam);

            }

            if(pivotIndex + 1 < param.get("endIndex")){

                Map<String, Integer> rightParam = new HashMap<String, Integer>();

                rightParam.put("startIndex", pivotIndex + 1);

                rightParam.put("endIndex", param.get("endIndex"));

                quickSortStack.push(rightParam);

            }

        }

    }

    private static int partition(int[] arr, int startIndex, int endIndex) {

        // 取第一个位置的元素作为基准元素

        int pivot = arr[startIndex];

        int left = startIndex;

        int right = endIndex;

        while( left != right) {

            //控制right指针比较并左移

            while(left<right && arr[right] > pivot){

                right--;

            }

            //控制right指针比较并右移

            while( left<right && arr[left] <= pivot) {

                left++;

            }

            //交换left和right指向的元素

            if(left<right) {

                int p = arr[left];

                arr[left] = arr[right];

                arr[right] = p;

            }

        }

        //pivot和指针重合点交换

        int p = arr[left];

        arr[left] = arr[startIndex];

        arr[startIndex] = p;

        return left;

    }

    public static void main(String[] args) {

        int[] arr = new int[] {4,7,6,5,3,2,8,1};

        quickSort(arr, 0, arr.length-1);

        System.out.println(Arrays.toString(arr));

    }
}
