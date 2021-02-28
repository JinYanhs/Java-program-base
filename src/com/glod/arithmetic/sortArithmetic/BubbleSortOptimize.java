package com.glod.arithmetic.sortArithmetic;

/**
 * @description: 冒泡排序 稳定
 * @author: Glod
 * @date: 2021/2/5
 */
public class BubbleSortOptimize {


    public static void main(String[] args) {
//        int[] list = {45,6,1,677,4,0,10,4,7,5,2,445,35,4,1};
        int[] list = SortUtil.generateArray(100000, 100000);
        bubbleSort(list);
    }

    /**
     * 冒泡排序
     *
     * @param resList 源数组
     */
    private static void bubbleSort(int[] resList) {
        long startTime = System.currentTimeMillis();
        for (int i = 0; i < resList.length - 1; i++) {
            boolean flag = true; // 标志位
            for (int j = 0; j < resList.length - 1 - i; j++) {
                if (resList[j] > resList[j + 1]) {
                    resList[j] = resList[j] ^ resList[j + 1];
                    resList[j + 1] = resList[j] ^ resList[j + 1];
                    resList[j] = resList[j + 1] ^ resList[j];
                    flag = false;
                }
            }
            // 不加 22830 ms
//            if (flag){ // 22168 ms
//                break;
//            }
        }
        long endTime = System.currentTimeMillis();
        System.out.println("总耗时：" + (endTime - startTime) + "ms");
        for (int v : resList) {
            System.out.println(v);
        }

    }

}
