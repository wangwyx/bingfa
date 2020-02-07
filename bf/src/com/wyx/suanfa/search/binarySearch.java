package com.wyx.suanfa.search;

import java.util.HashMap;

/**
 * @auth: wangyx-p
 * @date :2019/12/12 15:21
 * @Description:二分查找
 */
public class binarySearch {
    public static int binarySearch(int []array,int target){
        //查找范围起点
        int start=0;
        //查找范围终点
        int end=array.length-1;
        //查找范围中位数
        int mid;
        //迭代进行二分查找
        while(start<=end){
            mid=(start+end)/2;
            if(array[mid]==target){
                return mid;
            }else if(array[mid]<target){
                start=mid+1;
            }else{
                end=mid-1;
            }
        }
        return -1;
    }

    public int bsearch(int[] a, int n, int value) {
        int low = 0;
        int high = n - 1;

        while (low <= high) {
            int mid = (low + high) / 2;
            if (a[mid] == value) {
                return mid;
            } else if (a[mid] < value) {
                low = mid + 1;
            } else {
                high = mid - 1;
            }
        }

        return -1;
    }

    // 查找第一个相等的元素
    static int findFirstEqual(int[] array, int key) {
        int left = 0;
        int right = array.length - 1;

        // 这里必须是 <=
        while (left <= right) {
            int mid = (left + right) / 2;
            if (array[mid] >= key) {
                right = mid - 1;
            }
            else {
                left = mid + 1;
            }
        }
        if (left < array.length && array[left] == key) {
            return left;
        }

        return -1;
    }

    // 查找最后一个相等的元素
    static int findLastEqual(int[] array, int key) {
        int left = 0;
        int right = array.length - 1;

        // 这里必须是 <=
        while (left <= right) {
            int mid = (left + right) / 2;
            if (array[mid] <= key) {
                left = mid + 1;
            }
            else {
                right = mid - 1;
            }
        }
        if (right >= 0 && array[right] == key) {
            return right;
        }

        return -1;
    }

    // 查找最后一个等于或者小于key的元素
    static int findLastEqualSmaller(int[] array, int key) {
        int left = 0;
        int right = array.length - 1;

        // 这里必须是 <=
        while (left <= right) {
            int mid = (left + right) / 2;
            if (array[mid] > key) {
                right = mid - 1;
            }
            else {
                left = mid + 1;
            }
        }
        return right;
    }

    // 查找最后一个小于key的元素
    static int findLastSmaller(int[] array, int key) {
        int left = 0;
        int right = array.length - 1;

        // 这里必须是 <=
        while (left <= right) {
            int mid = (left + right) / 2;
            if (array[mid] >= key) {
                right = mid - 1;
            }
            else {
                left = mid + 1;
            }
        }
        return right;
    }

    // 查找第一个大于key的元素
    static int findFirstLarger(int[] array, int key) {
        int left = 0;
        int right = array.length - 1;

        // 这里必须是 <=
        while (left <= right) {
            int mid = (left + right) / 2;
            if (array[mid] > key) {
                right = mid - 1;
            }
            else {
                left = mid + 1;
            }
        }
        return left;
    }


    // 二分查找的递归实现
    public int bsearch1(int[] a, int n, int val) {
        return bsearchInternally(a, 0, n - 1, val);
    }

    private int bsearchInternally(int[] a, int low, int high, int value) {
        if (low > high) return -1;

        int mid =  low + ((high - low) >> 1);
        if (a[mid] == value) {
            return mid;
        } else if (a[mid] < value) {
            return bsearchInternally(a, mid+1, high, value);
        } else {
            return bsearchInternally(a, low, mid-1, value);
        }
    }

    /**
     * “旋转数组”中的二分查找
     * 可以查看自己的笔记二分查找
     * @param array
     * @param target
     * @return
     */
    public static int rotatedBinarySearch(int[] array, int target){
        int start = 0, end = array.length-1;
        while(start<=end){
            int mid = start + (end-start)/2;
            if(array[mid]==target){
                return mid;
            }
            //情况A：旋转点在中位数右侧
            if(array[mid]>=array[start]){
                //最左侧元素 <= 查找目标 < 中位数
                if(array[mid]>target && array[start]<=target){
                    end = mid - 1;
                } else {
                    start = mid + 1;
                }
            }

            //情况B：旋转点在中位数左侧，或与中位数重合
            else {
                //中位数 < 查找目标 <= 最右侧元素
                if(array[mid]<target && target<=array[end]){
                    start = mid + 1;
                } else {
                    end = mid - 1;
                }
            }
        }
        return -1;
    }

    /**
     *如何找到两个数组的中位数
     * @param arrayA
     * @param arrayB
     * @return
     */
    public static double findMedianSortedArrays(int[] arrayA, int[] arrayB) {
        int m = arrayA.length;
        int n = arrayB.length;
        //如果数组A的长度大于等于数组B，则交换数组
        if (m > n) {
            int[] temp = arrayA;
            arrayA = arrayB;
            arrayB = temp;
            int tmp = m;
            m = n;
            n = tmp;
        }
        int start = 0;
        int end = m;
        int mid = (m + n + 1) / 2;
        while (start <= end) {
            int i = (start + end) / 2;
            int j = mid - i;
            if (i < end && arrayB[j-1] > arrayA[i]){
                //i偏小了，需要右移
                start = i + 1;
            }
            else if (i > start && arrayA[i-1] > arrayB[j]) {
                //i偏大了，需要左移
                end = i - 1;
            }
            else {
                //i刚好合适
                int maxLeft;
                if (i == 0) {
                    //数组A的元素都大于数组B的情况
                    maxLeft = arrayB[j-1];
                } else if (j == 0) {
                    //数组A的元素都小于数组B的情况
                    maxLeft = arrayA[i-1];
                } else {
                    maxLeft = Math.max(arrayA[i-1], arrayB[j-1]);
                }
                if ( (m + n) % 2 == 1 ) {
                    //如果大数组的长度是奇数，中位数就是左半部分的最大值
                    return maxLeft;
                }
                int minRight;
                if (i == m) {
                    minRight = arrayB[j];
                } else if (j == n) {
                    minRight = arrayA[i];
                } else {
                    minRight = Math.min(arrayB[j], arrayA[i]);
                }
                //如果大数组的长度是偶数，取左侧最大值和右侧最小值的平均
                return (maxLeft + minRight) / 2.0;
            }
        }
        return 0.0;
    }

    /**
     * 算出一共有几个和为 k 的子数组
     * @param nums
     * @param k
     * @return
     */
    int subarraySum(int[] nums, int k) {
        int n = nums.length;
        // 构造前缀和
        int[] sum = new int[n + 1];
        sum[0] = 0;
        for (int i = 0; i < n; i++)
            sum[i + 1] = sum[i] + nums[i];

        int ans = 0;
        // 穷举所有子数组
        for (int i = 1; i <= n; i++)
            for (int j = 0; j < i; j++)
                // sum of nums[j..i-1]
                if (sum[i] - sum[j] == k)
                    ans++;

        return ans;
    }

    /**
     * 升级版
     * @param nums
     * @param k
     * @return
     */
    int subarraySum2(int[] nums, int k) {
        int n = nums.length;
        //map:前缀和：前缀和出现的次数
        HashMap<Integer, Integer> preSum = new HashMap<>();
        preSum.put(0, 1);
        int ans = 0, sum0_i = 0;
        for (int i = 0; i < n; i++) {
            sum0_i += nums[i];
            //设置我们想找的前缀和 nums[0...j]
            int sum0_j = sum0_i - k;
            //如果前面有这个前缀和，则直接更新答案
            if (preSum.containsKey(sum0_j)) {
                ans += preSum.get(sum0_j);
            }
            //把前缀和 nums[0...j] 加入记录并记录出现次数
            preSum.put(sum0_i, preSum.getOrDefault(sum0_i, 0) + 1);
        }
        return ans;
    }

    public static void main(String[] args) {
        int[] array = new int[1000];
        for(int i=0; i<1000;i++){
            array[i] = i;
        }
        System.out.println(binarySearch(array, 173));

        int[] array1 = new int[]{9,10,11,12,13,1,3,4,5,8};
        System.out.println(rotatedBinarySearch(array1, 12));

        int[] arrayA = new int[]{3,5,6,7,8,12,20};
        int[] arrayB = new int[]{1,10,17,18};
        System.out.println(findMedianSortedArrays(arrayA, arrayB));
    }
}
