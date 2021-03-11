package com.glod.collect.other;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * @description: collections工具类
 * @author: Glod
 * @date: 2021/3/8
 */
public class TestCollections {
    public static void main(String[] args) {
        // 给集合快速复制
        List<Integer> list = new ArrayList<>();
        Collections.addAll(list,54,544,16,1,1,12,1,7,49); // Collection<? super T> c  T....elements 可变参数
                                                                            // 底层通过foreach添加
        System.out.println(list);
        //排序
        Collections.sort(list);
        System.out.println(list);

        //查找元素（元素必须有序）
        int fiftyFourIndex = Collections.binarySearch(list,54); // 折半查找
        System.out.println(fiftyFourIndex);
        //最大、最小值
        System.out.println(Collections.max(list));
        System.out.println(Collections.min(list));
        //填充元素
        //Collections.fill(list,100);
        System.out.println(list);
        //复制集合 原集合覆盖目的集合从下标0开始
        List<Integer> list2 = new ArrayList<>();
        Collections.addAll(list2,0,0,0,0,0,0,0,0,0,0,0,0,0,0); // list2长度不能小于list长度，否则IOOBExcetion
        Collections.copy(list2,list); // dest size >= source size
        System.out.println(list2);
        // 同步集合
        StringBuffer buffer;//线程同步
        StringBuilder stringbuilder; // 未进行同步
 // default 当前包

        ArrayList list3;// 线程不安全 没有上锁 多线程操作会有线程安全问题 单线程没有问题
        List<Integer> synList = Collections.synchronizedList(list); // 返回一个安全的集合

        list.add(344);

    }
}
