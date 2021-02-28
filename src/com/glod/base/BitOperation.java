package com.glod.base;

/**
 * @description: 位运算相关
 * @author: Glod
 * @date: 2021/2/28
 */
public class BitOperation {
    /**
     *  源码->反码->补码
     *
     *   与（&）
     *   或（|）
     *   非（~）
     *   异或（^）
     *   左移（<<)
     *   右移（>>）
     *   无符号右移 （>>>）
     *   无符号左移 （<<<）
     *
     */

    public static void main(String[] args) {
        System.out.println((-1>>>1) == Integer.MAX_VALUE); // true
        System.out.println(-2 << 2); // 8
        System.out.println(~2); // 0000 0010

        System.out.println(1 << 30);  // HashMap ->  MAXIMUM_CAPACITY  1073741824

                System.out.println(judgeNum(1));
        System.out.println(judgeNum(0));
        System.out.println(judgeNum(-2));
        System.out.println(judgeNum(3313));
        System.out.println(judgeNum(11));
        System.out.println(judgeNum(16));

        System.out.println((220 % 8) == (220 & (8-1))); // 取模运算 num % num2 == num & (num - 1) 前提num2为2的n次方
    }

    /**
     * 判断奇偶数
     * @param num 任意
     * @return 1：偶数；0：奇数
     */
    private static String judgeNum(int num){
        if (num == 0) return "非奇非偶";
        if ((num & 1) == 0)return "偶数";
        if ((num & 1) == 1) return "奇数";
       throw new RuntimeException("没有结果");

    }
}
