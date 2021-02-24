package com.glod.collect.list;

import java.io.PrintStream;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * @description: arrayList存储多个学生分数
 * @author: Glod
 * @date: 2021/2/24
 */
public class TestArrayList {
    /**
     * ArrayList数据特点：不唯一、索引有序
     *
     *  数组中既可以放基本数据类型，也可以放引用数据类型，比如 int arr[] Student[] s
     *  集合中只能放引用类型数据类型，不能放基本类型数据，但可放包装类型数据
     *
     *  泛型generic的目的:解决宽进严出
     *   目标：安全、简单
     *    严进宽出
     *
     *    索引 指针
     *
     *    默认空，初始10，扩容50%（底层：长度可动态增长的Object数组)
     */

    public static void main(String[] args) {
        // 创建一个集合对象
        List<Integer> list = new ArrayList<Integer>();
        // 添加元素
        list.add(80);
        list.add(90);
        list.add(80); // 末尾添加元素

        list.add(1,90); // index 1 添加90 底层发生大量的元素后移操作，并且可能发生数组的扩容
        // 元素的数量
        System.out.println(list.size());

        // 获取指定索引的元素
        int elem = list.get(1);
        System.out.println(elem);

        // 遍历元素
        System.out.println(list);
        // 1.for-each循环
        for (int v : list) {
            PrintStream ps = System.out;
            ps.print(v + " ");
            System.out.println();
        }
        // 2. for循环
        for (int i = 0; i < list.size() ; i++){
            int element = list.get(i);
            System.out.println(i + "---->" + element);
        }
        // 3. Iterator
        System.out.println("-------------Iterator-----------");
        Iterator iterator = list.iterator();
        while (iterator.hasNext()){
            System.out.println(iterator.next());
        }
        // 4. Lambda表达式 + 流式编程
        System.out.println("-------------Lambda-----------");
//        list.forEach(v->{
//            System.out.println(v);
//        });
        list.forEach(System.out::println);

        // 追加一个list
        List<Integer> list2 = new ArrayList();
        list2.add(49);
        list2.add(60);
        list2.add(61);
        list2.add(80);
        System.out.println("-----------追加list-----------");
       list.addAll(list2); // 加到list后
        //list.addAll(2,list2);
        //list.removeAll(list2); // 从list中去掉list2集合中的元素，两个集合共有的元素
        //list.retainAll(list2); //(在list中删除list2中没有的元素） 在list中存在与list2共有的元素，可能会有重复的
        System.out.println("list2: "  + list2);
        System.out.println("list: " + list);

        System.out.println("------------删除元素------------");
        System.out.println("early" + list);
        //list.remove(2); // 删除指定索引的,发生大量元素前移
        list.remove(new Integer(90)); // 删除指定的整数数据，只会删掉第一个遇到的
        //list.clear(); //删除所有
        System.out.println("last" + list);

        System.out.println("-----------修改元素--------------");
        list.set(2,1); // 索引为2改为1
        System.out.println(list);

        System.out.println("----------其他------------");
        System.out.println(list);
        System.out.println(list.contains(90)); // 底层 return indexOf(o) >= 0;
        System.out.println(list.indexOf(8)); // 找索引，无为-1 底层：遍历—>equals方法

        list.isEmpty(); // 判空

        ArrayList arrayList = new ArrayList(-100); //java.lang.IllegalArgumentException: Illegal Capacity: -100



        // implement Iterator 在 ArrayList的内部类，好处，可直接访问外部类ArrayList的元素
    }
}
