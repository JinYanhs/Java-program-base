package com.glod.thread.chapter1;

/**
 * @description: 伪共享1 |缓存行 8 - 9ms
 * @author: Glod
 * @date: 2021/2/2
 */
public class ForContentTest {
    /**

     *
     *  多线程下：变量x、y同时被放到了CPU的一级和二级缓存，当线程1使用CPU对变量x更新时，首先会修改CPU1的一
     *  级缓存变量x所在的缓存行，这时候在缓存一致性协议下，CPU2中变量x对应的缓存行失效，那么线程2在写入变量
     *  x时就只能去二级缓存里查找，这就破坏了一级缓存。一级快于二级
     *
     *   *  CPU - Cache1 - Cache2 - 主内存 一级缓存快于二级缓存，cpu只有一级缓存话，会造成频繁地访问主内存
     *
     *  伪共享1比2快
     *
     *  缓存与内存交换的数据单位就是缓存行（缓存行大小的内存放入缓存行）
     *
     *  因为数组内数组元素的内存地址是连续的，当访问数组第一个元素时，如果当前元素在缓存没有命中，
     *  那么会从主内存一下子读取后续若干元素到缓存，也就是一次内存访问可以让后面多次访问直接在缓存命中。
     *
     *  伪共享2是跳跃式的访问数组元素，非顺序，破坏了程序访问的局部性原则，且缓存是有容量控制的，当缓存
     *  满了时，会根据一定淘汰算法替换缓存行，这会导致从内存置换过来的缓存行的元素还没等到被读取就被替换掉了。
     *
     *
     *  单线程下顺序修改一个缓存行中的多个变量，会充分利用程序运行的局部性原则，从而加速了程序的运行。
     *  多线程下并发修改一个缓存行中的多个变量时就会竞争缓存行，从而降低程序运行性能。
     *
     *
     */

    static final int LINE_NUM = 1024;
    static final int COLUM_NUM = 1024;

    public static void main(String[] args) {
        long [][] array = new long[LINE_NUM][COLUM_NUM];

        long startTime = System.currentTimeMillis();
        for (int i = 0;i < LINE_NUM; ++i){
            for (int j = 0; j < COLUM_NUM; ++j){
                array[i][j] = i * 2 + j;
            }
        }
        long endTime = System.currentTimeMillis();
        long cacheTime = endTime - startTime;
        System.out.println("cache time :" + cacheTime);


    }





}
