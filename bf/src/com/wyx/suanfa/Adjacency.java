package com.wyx.suanfa;


public class Adjacency {
   public final int INFINITE = 99999;
   public int[][] Graph_Matrix;         
   // 构造函数
   public Adjacency(int[][] Weight_Path,int number) {
      int i, j;      
      int Start_Point, End_Point;            
      Graph_Matrix = new int[number][number]; 
      for ( i = 1; i < number; i++ )
         for ( j = 1; j < number; j++ )
            if ( i != j )
               Graph_Matrix[i][j] = INFINITE;     
            else
               Graph_Matrix[i][j] = 0;            
         for ( i = 0; i < Weight_Path.length; i++ ) { 
            Start_Point = Weight_Path[i][0];           
            End_Point = Weight_Path[i][1];             
            Graph_Matrix[Start_Point][End_Point] = Weight_Path[i][2];
         }
   }
   // 显示图形的方法
   public void printGraph_Matrix() {
      for ( int i = 1; i < Graph_Matrix.length; i++ ) {
         for ( int j = 1; j < Graph_Matrix[i].length; j++ )
            if ( Graph_Matrix[i][j] == INFINITE )
               System.out.print(" x ");
            else {  
                 if ( Graph_Matrix[i][j] == 0 ) System.out.print(" ");
                 System.out.print(Graph_Matrix[i][j] + " ");
            } 
            System.out.println();
      }
   }
}