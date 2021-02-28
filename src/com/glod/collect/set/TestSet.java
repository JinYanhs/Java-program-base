package com.glod.collect.set;

import java.util.*;

/**
 * @description: 探究Set
 * @author: Glod
 * @date: 2021/2/27
 */
public class TestSet {

    /**
     *   * Set的底层都是Map
     *  Set feature: 无序、唯一（不重复）
     *
     *  HashSet：采用Hashtable哈希表存储结构(散列表-》神奇的结构）
      *     adv: 添加速度快，查询速度快，删除速度快 就是快
     *      disadvantage: 无序
     *
     *  LinkedHashSet
     *     采用哈希表存储结构，同时使用链表维护次序
     *     有序（添加顺序）
     *     链表-》有序-》结构复杂导致速度慢
     *
     *   TreeSet: 采用二叉树（红黑树）、二叉查找树 平衡树 左子树小于根，右子树大于根
     *   adv: 有序，查询虚度比List快（按照内容查询）
     *   disadvantage: 查询速度没有HashSet快
     *
     *
     *   * Set无序，相比Collection没有增加任何方法，List相比Collection增加了索引相关的方法（get(i) set(i,elem) remove(i,elem)

     */

    public static void main(String[] args) {
//        Set<String> set = new HashSet(); // 快 无序 唯一  哈希表
//        Set<String> set = new LinkedHashSet(); // 快 有序（添加顺序） 唯一 哈希表 + 链表
        Set<String> set = new TreeSet(); // 快 有序（自然顺序-》例子为字母大小顺序） 唯一  红黑树


        set.add("MySQL");
        set.add("JavaSE");
        set.add("Web");
        set.add("JavaEE");
        set.add("A");
        set.add("a");
        set.add("b");
        set.add("C");
        System.out.println(set.size());
        System.out.println(set);

        // 遍历set
        // 方法一：for-each
        System.out.println("-----------for-each-----------");
        for (String elem: set){
            System.out.println(elem);
        }

        // 方法二：Iterator
        System.out.println("-----------Iterator-----------");
        Iterator<String> iterator = set.iterator();
        while (iterator.hasNext()){
            System.out.println( iterator.next());
        }

        // 方法三： lambda + 流式编程
        System.out.println("-----------lambda + 流式编程-----------");
        set.forEach(System.out::println);

    }
}
