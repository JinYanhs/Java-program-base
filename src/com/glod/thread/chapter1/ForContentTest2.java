package com.glod.thread.chapter1;

/**
 * @description: 伪共享2 |缓存行 12 - 17 ms
 * @author: Glod
 * @date: 2021/2/2
 */
public class ForContentTest2 {

    static final int LINE_NUM = 1024;
    static final int COLUM_NUM = 1024;

    public static void main(String[] args) {

        long array2[][] = new long[LINE_NUM][COLUM_NUM];
        long startTime2 = System.currentTimeMillis();
        for (int i = 0; i < COLUM_NUM; ++i){
            for (int j = 0 ; j < LINE_NUM; ++j){
                array2[j][i] = i * 2 + j;
            }
        }
        long endTime2 = System.currentTimeMillis();
        long cacheTime2 = endTime2 - startTime2;
        System.out.println("cache time2 :" + cacheTime2);
    }

}
