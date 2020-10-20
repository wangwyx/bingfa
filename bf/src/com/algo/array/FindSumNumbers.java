package com.algo.array;

import java.util.*;

/**
 * Ѱ��������2��֮��Ϊtarget ��Ԫ��
 *
 */
public class FindSumNumbers {

    public static List<List<Integer>> twoSum(int[] nums, int target) {
        Map<Integer, Integer> map = new HashMap<>();
        List<List<Integer>> resultList = new ArrayList<>();
        for (int i = 1; i < nums.length; i++) {
            map.put(nums[i], i);
        }
        for (int i = 0; i < nums.length; i++) {
            int other = target - nums[i];
            if (map.containsKey(other) && map.get(other) != i) {
                resultList.add(Arrays.asList(i,map.get(other)));
                //Ϊ��ֹ�ҵ��ظ���Ԫ�ضԣ�ƥ���ӹ�ϣ��ɾ����ӦԪ��
                map.remove(nums[i]);
            }
        }
        return resultList;
    }

    /**
     *
     * @param nums
     * @param target
     * @return
     */
    public static List<List<Integer>> twoSumV2(int[] nums, int target) {
        Map<Integer, Integer> map = new HashMap<>();
        List<List<Integer>> resultList = new ArrayList<>();
        for (int i = 0; i < nums.length; i++) {
            int other = target - nums[i];
            if (map.containsKey(other)) {
                resultList.add(Arrays.asList(map.get(other),i));
            }
            map.put(nums[i], i);
        }
        return resultList;
    }

    /**
     * Ѱ��������3��֮��Ϊtarget ��Ԫ��
     * @param nums
     * @param target
     * @return
     */
    public static List<List<Integer>> threeSum(int[] nums, int target) {
        List<List<Integer>> resultList = new ArrayList<>();
        for (int i = 0; i < nums.length; i++) {
            Map<Integer, Integer> map = new HashMap<>();
            int d1 = target - nums[i];
            //Ѱ������֮�͵���d1�����
            for (int j = i+1; j < nums.length; j++) {
                int d2 = d1 - nums[j];
                if (map.containsKey(d2)) {
                    resultList.add(Arrays.asList(nums[i], d2, nums[j]));
                }
                map.put(nums[j], j);
            }
        }
        return resultList;
    }

    public static List<List<Integer>> threeSumv2(int[] nums, int target) {
        Arrays.sort(nums);
        List<List<Integer>> resultList = new ArrayList<List<Integer>>();
        //��ѭ��
        for (int i = 0; i < nums.length; i++) {
            int d = target - nums[i];
            // j��k˫ָ��ѭ����λ��j����ˣ�k���Ҷ�
            for (int j=i+1,k=nums.length-1; j<nums.length; j++) {
                // kָ�������ƶ�
                while (j<k && (nums[j]+nums[k])>d) {
                    k--;
                }
                //˫ָ���غϣ���������ѭ��
                if (j == k) {
                    break;
                }
                if (nums[j] + nums[k] == d) {
                    List<Integer> list = Arrays.asList(nums[i], nums[j], nums[k]);
                    resultList.add(list);
                }
            }
        }
        return resultList;
    }

    public static void main(String[] args) {
        int[] nums = {5,12,6,3,9,2,1,7};
        List<List<Integer>> resultList = twoSum(nums, 13);
        for(List<Integer> list : resultList){
            System.out.println(Arrays.toString(list.toArray()));
        }
    }

}