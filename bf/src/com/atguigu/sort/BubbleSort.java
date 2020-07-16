package com.atguigu.sort;

import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.Stack;


public class BubbleSort {

	public static void main(String[] args) {
//		int arr[] = {3, 9, -1, 10, 20};
//		
//		System.out.println("排序前");
//		System.out.println(Arrays.toString(arr));
		
		//为了容量理解，我们把冒泡排序的演变过程，给大家展示
		
		//测试一下冒泡排序的速度O(n^2), 给80000个数据，测试
		//创建要给80000个的随机的数组
		int[] arr = new int[80000];
		for(int i =0; i < 80000;i++) {
			arr[i] = (int)(Math.random() * 8000000); //生成一个[0, 8000000) 数
		}
		
		Date data1 = new Date();
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String date1Str = simpleDateFormat.format(data1);
		System.out.println("排序前的时间是=" + date1Str);
		
		//测试冒泡排序
		bubbleSort(arr);
		
		Date data2 = new Date();
		String date2Str = simpleDateFormat.format(data2);
		System.out.println("排序后的时间是=" + date2Str);
		
		//System.out.println("排序后");
		//System.out.println(Arrays.toString(arr));
		
		
		/*
		
		// 第二趟排序，就是将第二大的数排在倒数第二位
		
		for (int j = 0; j < arr.length - 1 - 1 ; j++) {
			// 如果前面的数比后面的数大，则交换
			if (arr[j] > arr[j + 1]) {
				temp = arr[j];
				arr[j] = arr[j + 1];
				arr[j + 1] = temp;
			}
		}
		
		System.out.println("第二趟排序后的数组");
		System.out.println(Arrays.toString(arr));
		
		
		// 第三趟排序，就是将第三大的数排在倒数第三位
		
		for (int j = 0; j < arr.length - 1 - 2; j++) {
			// 如果前面的数比后面的数大，则交换
			if (arr[j] > arr[j + 1]) {
				temp = arr[j];
				arr[j] = arr[j + 1];
				arr[j + 1] = temp;
			}
		}

		System.out.println("第三趟排序后的数组");
		System.out.println(Arrays.toString(arr));
		
		// 第四趟排序，就是将第4大的数排在倒数第4位

		for (int j = 0; j < arr.length - 1 - 3; j++) {
			// 如果前面的数比后面的数大，则交换
			if (arr[j] > arr[j + 1]) {
				temp = arr[j];
				arr[j] = arr[j + 1];
				arr[j + 1] = temp;
			}
		}

		System.out.println("第四趟排序后的数组");
		System.out.println(Arrays.toString(arr)); */
		
	}
	
	// 将前面额冒泡排序算法，封装成一个方法
	public static void bubbleSort(int[] arr) {
		// 冒泡排序 的时间复杂度 O(n^2), 自己写出
		int temp = 0; // 临时变量
		boolean flag = false; // 标识变量，表示是否进行过交换
		for (int i = 0; i < arr.length - 1; i++) {

			for (int j = 0; j < arr.length - 1 - i; j++) {
				// 如果前面的数比后面的数大，则交换
				if (arr[j] > arr[j + 1]) {
					flag = true;
					temp = arr[j];
					arr[j] = arr[j + 1];
					arr[j + 1] = temp;
				}
			}
			//System.out.println("第" + (i + 1) + "趟排序后的数组");
			//System.out.println(Arrays.toString(arr));

			if (!flag) { // 在一趟排序中，一次交换都没有发生过
				break;
			} else {
				flag = false; // 重置flag!!!, 进行下次判断
			}
		}
	}

	public static void bubbleSort2(int[] arras){

		int lastSwappedIndex = 0; //记录最后一次交换的位置
		int sortBorder = arras.length - 1; //将有序和无序部分的边界初始化为最后一个元素
		for (int i = 0; i < arras.length-1; i++) {
			boolean swapFlag = false;
			for (int j = 0; j < sortBorder; j++) {
				if (arras[j] > arras[j + 1]) {
					int temp = arras[j];
					arras[j] = arras[j + 1];
					arras[j + 1] = temp;
					swapFlag = true;
					lastSwappedIndex = j;
				}
			}
			sortBorder = lastSwappedIndex;
			if (!swapFlag) {
				break;
			}
			System.out.println("外部循环i:"+i);
		}
		System.out.println(Arrays.toString(arras));
	}

	// 使用栈进行冒泡排序
	public static void bubbleSortStack(int arr[], int n) {
		Stack<Integer> s1 = new Stack<>();

		// 将 arr 中的所有元素压入栈 s1
		for (int num : arr)
			s1.push(num);

		Stack<Integer> s2 = new Stack<>();

		for (int i = 0; i < n; i++) {
			// 初始时 s1 不为空，使用i 的奇偶来决定将哪一个栈中的元素转移到另外一个栈
			if (i % 2 == 0){
				while (!s1.isEmpty()){
					int t = s1.pop();

					if (s2.isEmpty())
						s2.push(t);
					else{
						if (s2.peek() > t){
							// 交换操作
							int temp = s2.pop();
							s2.push(t);
							s2.push(temp);
						}else{
							s2.push(t);
						}
					}
				}

				// 将找到的最大元素放到正确的位置 n-i-1
				arr[n-1-i] = s2.pop();
			}else{
				while(!s2.isEmpty())
				{
					int t = s2.pop();

					if (s1.isEmpty())
						s1.push(t);
					else{
						if (s1.peek() > t){
							int temp = s1.pop();
							s1.push(t);
							s1.push(temp);
						}else
							s1.push(t);
					}
				}
				arr[n-1-i] = s1.pop();
			}
		}
		System.out.println(Arrays.toString(arr));
	}

}
