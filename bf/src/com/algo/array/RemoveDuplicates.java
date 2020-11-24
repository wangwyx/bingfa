package com.algo.array;

import java.util.Arrays;

/**
 * @auth: wangyx-p
 * @date :2020-11-24 10:06
 * @Description:
 * http://mp.weixin.qq.com/s?__biz=MzIxMjE5MTE1Nw==&mid=2653215473&idx=1&sn=35327bf32dff846b72149888fdd91063&chksm=8c99a82bbbee213de56d25c03f4db4e32785a03ff7f2bf683d8a6211e174bb4b2bdf32d84a90&mpshare=1&scene=24&srcid=1102AK8yPerpTyrq3T4nwsuq&sharer_sharetime=1604296982596&sharer_shareid=933be855255b9ebb501cdc6459a0fb73#rd
 */
public class RemoveDuplicates {
    public static int maxProfitFor1Time(int prices[]) {
        if(prices==null || prices.length==0) {
            return 0;
        }
        int minPrice = prices[0];
        int maxProfit = 0;
        for (int i = 1; i < prices.length; i++) {
            if (prices[i] < minPrice) {
                minPrice = prices[i];
            } else if(prices[i] - minPrice > maxProfit){
                maxProfit = prices[i] - minPrice;
            }
        }
        return maxProfit;
    }


    public static int maxProfitForAnyTime(int[] prices) {
        int maxProfit = 0;
        for (int i = 1; i < prices.length; i++) {
            if (prices[i] > prices[i-1])
                maxProfit += prices[i] - prices[i-1];
        }
        return maxProfit;
    }

    /**原地删除，被删除的元素用最后一个元素替换
     * 删除数组中的指定元素，并返回删除后的数组真实长度
     * https://mp.weixin.qq.com/s?__biz=MzI2NjI5MzU2Nw==&mid=2247489646&idx=2&sn=0ea41f2dc8faed0ececec2078b7763f1&chksm=ea91003edde68928c66bcb669025241c08fffe8afbb87edc1a59edd0451ec13d44e73b7bb85f&mpshare=1&scene=24&srcid=0823IlDn4MzMIdV2tEC6hotr&sharer_sharetime=1598163747586&sharer_shareid=933be855255b9ebb501cdc6459a0fb73#rd

     http://mp.weixin.qq.com/s?__biz=MzIxMjE5MTE1Nw==&mid=2653215473&idx=1&sn=35327bf32dff846b72149888fdd91063&chksm=8c99a82bbbee213de56d25c03f4db4e32785a03ff7f2bf683d8a6211e174bb4b2bdf32d84a90&mpshare=1&scene=24&srcid=1102AK8yPerpTyrq3T4nwsuq&sharer_sharetime=1604296982596&sharer_shareid=933be855255b9ebb501cdc6459a0fb73#rd
     * @param nums
     * @param val
     * @return
     */
    public static int removeElement(int[] nums, int val) {
        int j = nums.length;
        int i = 0;
        while (i < j) {
            if (nums[i] == val) {
                nums[i] = nums[j-1];
                System.out.println(nums[i]);
                j--;
            } else {
                i++;
            }
        }
        System.out.println(Arrays.toString(nums));
        return j;
    }

    /**原地删除，将重复的元素移动到最后
     * 删除数组中的重复元素，返回删除后的数组真实长度，多余的长度不用处理
     * https://mp.weixin.qq.com/s?__biz=MzI1MTIzMzI2MA==&mid=2650568885&idx=1&sn=2e5e9a345a3266bbf9d694be87a3545b&chksm=f1fece36c68947201b63ecaaa285ebb31bdbce58aa63eb79ff073b689f51af839df39bf7d2c0&mpshare=1&scene=24&srcid=1102VYJdD04T533gE0fzUvnn&sharer_sharetime=1604296902124&sharer_shareid=933be855255b9ebb501cdc6459a0fb73#rd
     * @param nums
     * @return
     */
    public static int removeDuplicates(int[] nums) {
        int n = nums.length;
        if (n == 0) {
            return 0;
        }
        int slow = 0, fast = 1;
        while (fast < n) {
            if (nums[fast] != nums[slow]) {
                slow++;
                nums[slow] = nums[fast];
            }
            fast++;
        }
        System.out.println(Arrays.toString(nums));
        return slow + 1;
    }

    public static void main(String[] args) {
        int[] prices = {9,2,7,4,3,1,8,4};
        System.out.println(prices.length);
        System.out.println(maxProfitFor1Time(prices));
        System.out.println(maxProfitForAnyTime(prices));
        System.out.println(removeElement(prices,3));
        System.out.println(removeDuplicates(new int[]{0,0,1,1,2,3,4}));
    }
}
