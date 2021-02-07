package com.glod.arithmetic.sortArithmetic;

/**
 * @description: 排序工具类
 * @author: Glod
 * @date: 2021/2/7
 */
public class SortUtil {

    /**
     *  生成随机数组
     *
     * @param len 总长度
     * @param max 最大值
     * @return
     */
    public static int[] generateArray(int len,int max){
        int[] arr = new int[len];
        for (int i = 0; i < arr.length; i++){
            arr[i] = (int)(Math.random() * max);
        }
        return arr;
    }
}
