package com.glod.arithmetic.leetcode;

import java.util.Arrays;

/**
 * @description: 数组中占比超过一半的元素称之为主要元素。给定一个整数数组，找到它的主要元素。若没有，返回-1。
 * @author: Glod
 * @date: 2021/2/24
 */
public class MajorityElement {
    /**
     * 示例 1：
     *
     * 输入：[1,2,5,9,5,9,5,5,5]
     * 输出：5
     *  
     *
     * 示例 2：
     *
     * 输入：[3,2]
     * 输出：-1
     *  
     *
     * 示例 3：
     *
     * 输入：[2,2,1,1,1,2,2]
     * 输出：2
     *
     * 来源：力扣（LeetCode）
     * 链接：https://leetcode-cn.com/problems/find-majority-element-lcci
     * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
     *
     */

    public static void main(String[] args) {
        int[] nums = {4,2,2,2,4,3,2,2,3,2,3};
        System.out.println(majorityElement(nums));
    }

    private static  int majorityElement(int[] nums){
        if(nums.length == 0){
            return -1;
        }
        long halfSize = nums.length / 2;
        Arrays.sort(nums);
        int xorNum = nums[0];
        int count = 0;
        for (int n : nums){
            int res = xorNum ^ n;
            if (res == 0){
                count ++;
                if (count > halfSize){
                    return xorNum;
                }

            }else{
                xorNum = n;
                count = 1;
            }
        }
        return  -1;
    }
}
