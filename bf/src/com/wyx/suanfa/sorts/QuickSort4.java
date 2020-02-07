package com.wyx.suanfa.sorts;

/**
 * @auth: wangyx-p
 * @date :2019/12/12 11:02
 * @Description:
 */
public class QuickSort4 {

        private static int getIndex(int[] arr, int low, int high) {
            int index = arr[low];
            while (low < high) {
                while (low < high && arr[high] >= index)
                    high--;
                arr[low] = arr[high];
                while (low < high && arr[low] <= index)
                    low++;
                arr[high] = arr[low];
            }
            arr[low] = index;


            return low;

        }

        public static void sort(int[] arr, int low, int high) {
            if (low < high) {
                int index = getIndex(arr, low, high);
                sort(arr, 0, index - 1);
                sort(arr, index + 1, high);
            }
        }

        public static void main(String[] args) {
            int[] arr = { 6, 7, 8, 2, 4, 3, 1};
            sort(arr, 0, arr.length - 1);
            for (int n : arr)
                System.out.print(n + " ");
        }


}
