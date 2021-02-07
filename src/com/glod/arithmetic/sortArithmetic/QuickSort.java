package com.glod.arithmetic.sortArithmetic;

/**
 * @description: 快排 nlogn
 * @author: Glod
 * @date: 2021/2/4
 */
public class QuickSort {
	public static void main(String[] args) {
		  int a[] = SortUtil.generateArray(100000,100000);
				  //new int[50];
//          for(int i = 0 ; i<50 ;i++){
//        	  a[i] =(int) (Math.random()*100);
//          }
		  
		  System.out.println("排序前");
		  for (int n = 0; n < a.length; n++) {
		   System.out.print(a[n] + " ,");
		  }

		  QuickSort qs = new QuickSort();
		  qs.quickSort(a, 0, a.length - 1);
		  
		  System.out.println("\n排序后");
		  for (int n = 0; n < a.length; n++) {
		   System.out.print(a[n] + " ,");
		  }
		 }

		 private void quickSort(int[] a, int low, int high) {
		  int pivot = position(a,low,high); // 基准
		  if(pivot > low+1)
		   quickSort(a,low,pivot-1);
		  if(pivot < high-1)
		   quickSort(a,pivot+1,high);  
		 }

		 private int position(int[] a, int low, int high) {
		  while (low < high) {
		   while (low < high && a[high] >= a[low]) {
		    high--;
		   }
		   if (a[high] < a[low]) {
		    int temp = a[low];
		    a[low] = a[high];
		    a[high] = temp;
		   }
		   while (low < high && a[low] <= a[high]) {
		    low++;
		   }
		   if(a[low] > a[high]){
		    int temp = a[low];
		    a[low] = a[high];
		    a[high] = temp;
		   }
		  }
		  return low;
		 }
}
