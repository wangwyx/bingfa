package com.wyx.suanfa.Graph;// =============== Program Description ===============
// 程序名称： CH07_08.java                               
// 程序目的： Floyd算法(所有顶点两两之间的最短距离) 
// ===================================================

// 图形的相邻矩阵类声明
import com.wyx.suanfa.Adjacency;

// Floyd算法类
class Floyd extends Adjacency {
   private int[][] cost;
   private int capcity;
   // 构造函数
   public Floyd(int[][] Weight_Path,int number) {
      super(Weight_Path,number);
      cost = new int[number][]; 
      capcity=Graph_Matrix.length;
      for ( int i = 0; i < capcity; i++ )
         cost[i] = new int[number];
   }
   // 所有顶点两两之间的最短距离
   public void shortestPath() {
      for ( int i = 1; i < Graph_Matrix.length; i++ )
         for ( int j = i; j < Graph_Matrix.length; j++ )
            cost[i][j] = cost[j][i] = Graph_Matrix[i][j];
      for ( int k = 1; k < Graph_Matrix.length; k++ )
         for ( int i = 1; i < Graph_Matrix.length; i++ )
            for ( int j = 1; j < Graph_Matrix.length; j++ )
               if ( cost[i][k]+cost[k][j] < cost[i][j] )
                  cost[i][j] = cost[i][k] + cost[k][j];
      System.out.print("顶点 vex1 vex2 vex3 vex4 vex5 vex6\n");
      for ( int i = 1; i < Graph_Matrix.length; i++ ) {
         System.out.print("vex"+i + " ");
         for ( int j = 1; j < Graph_Matrix.length; j++ ) {
        	// 调整显示的位置, 显示距离数组
            if ( cost[i][j] < 10 ) System.out.print(" ");
            if ( cost[i][j] < 100 )System.out.print(" ");
            System.out.print(" " + cost[i][j] + " ");
         }
         System.out.println();
      }
   }
}
// 主类
public class CH07_08 {
	// 主程序
   public static void main(String[] args) {
      int Weight_Path[][] = { {1, 2, 10},{2, 3, 20}, 
                       {2, 4, 25},{3, 5, 18},
                       {4, 5, 22},{4, 6, 95},{5, 6, 77} };
      Floyd object = new Floyd(Weight_Path,7);  
      System.out.println("==========================");
      System.out.println("此范例图形的相邻矩阵如下: ");
      System.out.println("==========================");
      object.printGraph_Matrix();             
      System.out.println("==================================");
      System.out.println("所有顶点两两之间的最短距离: ");
      System.out.println("==================================");
      object.shortestPath();          
   }
}
