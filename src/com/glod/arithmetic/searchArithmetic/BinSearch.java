package com.glod.arithmetic.searchArithmetic;

/**
 * @description: 二分查找
 * @author: Glod
 * @date: 2021/2/4
 */
public class BinSearch {
    int mid;
	// 二分查找
	  public  int binSearch(int r[],int low,int high,int k){
		  if(low>high){
			  return  0;
		  }else{
			  mid = (low+high)/2;
			  if (k<r[mid]) 
		           return binSearch(r, low, mid-1, k);
		       else  if (k>r[mid]) 
		                   return binSearch(r, mid+1, high, k); 
		               else return mid;
		  }
	  }
	  
		public static void main(String[] args)  {
		
        int r[] = new int[]{1,3,5,7,9,10,13,18,31};
        BinSearch b = new BinSearch();
             System.out.println("数组中"+b.binSearch(r,0,8,10)+"位找到值");
	}
}
