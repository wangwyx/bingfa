package com.atguigu.sort;

import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.Stack;


public class BubbleSort {

	public static void main(String[] args) {
//		int arr[] = {3, 9, -1, 10, 20};
//		
//		System.out.println("����ǰ");
//		System.out.println(Arrays.toString(arr));
		
		//Ϊ��������⣬���ǰ�ð��������ݱ���̣������չʾ
		
		//����һ��ð��������ٶ�O(n^2), ��80000�����ݣ�����
		//����Ҫ��80000�������������
		int[] arr = new int[80000];
		for(int i =0; i < 80000;i++) {
			arr[i] = (int)(Math.random() * 8000000); //����һ��[0, 8000000) ��
		}
		
		Date data1 = new Date();
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String date1Str = simpleDateFormat.format(data1);
		System.out.println("����ǰ��ʱ����=" + date1Str);
		
		//����ð������
		bubbleSort(arr);
		
		Date data2 = new Date();
		String date2Str = simpleDateFormat.format(data2);
		System.out.println("������ʱ����=" + date2Str);
		
		//System.out.println("�����");
		//System.out.println(Arrays.toString(arr));
		
		
		/*
		
		// �ڶ������򣬾��ǽ��ڶ���������ڵ����ڶ�λ
		
		for (int j = 0; j < arr.length - 1 - 1 ; j++) {
			// ���ǰ������Ⱥ���������򽻻�
			if (arr[j] > arr[j + 1]) {
				temp = arr[j];
				arr[j] = arr[j + 1];
				arr[j + 1] = temp;
			}
		}
		
		System.out.println("�ڶ�������������");
		System.out.println(Arrays.toString(arr));
		
		
		// ���������򣬾��ǽ�������������ڵ�������λ
		
		for (int j = 0; j < arr.length - 1 - 2; j++) {
			// ���ǰ������Ⱥ���������򽻻�
			if (arr[j] > arr[j + 1]) {
				temp = arr[j];
				arr[j] = arr[j + 1];
				arr[j + 1] = temp;
			}
		}

		System.out.println("����������������");
		System.out.println(Arrays.toString(arr));
		
		// ���������򣬾��ǽ���4��������ڵ�����4λ

		for (int j = 0; j < arr.length - 1 - 3; j++) {
			// ���ǰ������Ⱥ���������򽻻�
			if (arr[j] > arr[j + 1]) {
				temp = arr[j];
				arr[j] = arr[j + 1];
				arr[j + 1] = temp;
			}
		}

		System.out.println("����������������");
		System.out.println(Arrays.toString(arr)); */
		
	}
	
	// ��ǰ���ð�������㷨����װ��һ������
	public static void bubbleSort(int[] arr) {
		// ð������ ��ʱ�临�Ӷ� O(n^2), �Լ�д��
		int temp = 0; // ��ʱ����
		boolean flag = false; // ��ʶ��������ʾ�Ƿ���й�����
		for (int i = 0; i < arr.length - 1; i++) {

			for (int j = 0; j < arr.length - 1 - i; j++) {
				// ���ǰ������Ⱥ���������򽻻�
				if (arr[j] > arr[j + 1]) {
					flag = true;
					temp = arr[j];
					arr[j] = arr[j + 1];
					arr[j + 1] = temp;
				}
			}
			//System.out.println("��" + (i + 1) + "������������");
			//System.out.println(Arrays.toString(arr));

			if (!flag) { // ��һ�������У�һ�ν�����û�з�����
				break;
			} else {
				flag = false; // ����flag!!!, �����´��ж�
			}
		}
	}

	public static void bubbleSort2(int[] arras){

		int lastSwappedIndex = 0; //��¼���һ�ν�����λ��
		int sortBorder = arras.length - 1; //����������򲿷ֵı߽��ʼ��Ϊ���һ��Ԫ��
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
			System.out.println("�ⲿѭ��i:"+i);
		}
		System.out.println(Arrays.toString(arras));
	}

	// ʹ��ջ����ð������
	public static void bubbleSortStack(int arr[], int n) {
		Stack<Integer> s1 = new Stack<>();

		// �� arr �е�����Ԫ��ѹ��ջ s1
		for (int num : arr)
			s1.push(num);

		Stack<Integer> s2 = new Stack<>();

		for (int i = 0; i < n; i++) {
			// ��ʼʱ s1 ��Ϊ�գ�ʹ��i ����ż����������һ��ջ�е�Ԫ��ת�Ƶ�����һ��ջ
			if (i % 2 == 0){
				while (!s1.isEmpty()){
					int t = s1.pop();

					if (s2.isEmpty())
						s2.push(t);
					else{
						if (s2.peek() > t){
							// ��������
							int temp = s2.pop();
							s2.push(t);
							s2.push(temp);
						}else{
							s2.push(t);
						}
					}
				}

				// ���ҵ������Ԫ�طŵ���ȷ��λ�� n-i-1
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
