package com.wyx.suanfa.Graph;// =============== Program Description ===============
// 程序名称： CH07_04.java                               
// 程序目的：先深后广搜索法(DFS) 
// ===================================================


class Node04
{
	int x;
    Node04 next;
	public Node04(int x)
	{
		this.x=x;
		this.next=null;
	}
}
class GraphLink04
{
	public Node04 first;
	public Node04 last;
	public boolean isEmpty()
	{
		return first==null;
	}
	public void print()
	{
        Node04 current=first;
		while(current!=null)
		{
			System.out.print("["+current.x+"]");
			current=current.next;

		}
		System.out.println();
	}
	public void insert(int x)
	{
        Node04 newNode=new Node04(x);
		if(this.isEmpty())
		{
			first=newNode;
			last=newNode;
		}
		else
		{
			last.next=newNode;
			last=newNode;
		}
	}
}

public class CH07_04
{	
	public static int run[]=new int[9];
	public static GraphLink04 Head[]=new GraphLink04[9];
	public static void dfs(int current)             //深度优先遍历子程序
	{
		run[current]=1;
		System.out.print("["+current+"]");
		
		while((Head[current].first)!=null)
		{
			if(run[Head[current].first.x]==0) //如果顶点尚未遍历，就进行dfs的递归调用
				dfs(Head[current].first.x);
			Head[current].first=Head[current].first.next;
		}
	}
	public static void main (String args[])
	{
		int Data[][] =		//图形边线数组声明

			{ {1,2},{2,1},{1,3},{3,1},{2,4},{4,2},{2,5},{5,2},{3,6},{6,3},
		      {3,7},{7,3},{4,5},{5,4},{6,7},{7,6},{5,8},{8,5},{6,8},{8,6} };
		int DataNum;			
		int i,j;				
		System.out.println("图形的邻接表内容："); //打印图形的邻接表内容
		for ( i=1 ; i<9 ; i++ )			    //共有8个顶点
		{
			run[i]=0;			    //设定所有顶点成尚未遍历过
			Head[i]=new GraphLink04();
			System.out.print("顶点"+i+"=>");
				for( j=0 ; j<20 ;j++)		    //二十条边线
				{
					if(Data[j][0]==i)  //如果起点和列表首相等，则把顶点加入列表
				{
					DataNum = Data[j][1];
					Head[i].insert(DataNum);
				}
			}
			Head[i].print();           //打印图形的邻接表内容
		}		
		System.out.println("深度优先遍历顶点：");   //打印深度优先遍历的顶点
		dfs(1);
		System.out.println("");
	}
}
