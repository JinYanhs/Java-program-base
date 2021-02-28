package com.glod.collect.set;

import java.util.Comparator;
import java.util.HashSet;
import java.util.Set;
import java.util.TreeSet;

/**
 * @description: 探究Set存储对象
 * @author: Glod
 * @date: 2021/2/27
 */
public class TestStudentSet {
    public static void main(String[] args) {
        /**
         *  Q1:为什么HashSet、LinkedHashSet存储String是唯一的，但是存储Student不唯一了
         *  solution: 同时重写Student的hashCode和equals方法
         *
         *  Q2:为什么TreeSet存储String是有序的，但是存储Student报异常：Exception in thread "main" java.lang.
         *      ClassCastException: com.glod.collect.set.Student cannot be cast to java.lang.Comparable
         *  原因：String做了，而student没有做
         *  solution: 实现Comparable接口
         */

        Set<Student> set = new HashSet(); // 同时重写Student的hashCode和equals方法会保证唯一
        Comparator comp = new StuScoreDescComparator(); // 作为TreeSet参数传入，外部比较器优先
        //Set<Student> set = new TreeSet(comp);  // Comparable->compareTo返回0的不会加入TreeSet去重


        Student student1 = new Student("张三","男",23,60);
        Student student2 = new Student("王五","男",25,65);
        Student student3 = new Student("老六","男",23,59);
        Student student4 = new Student("老四","男",30,60);
        Student student5 = new Student("张三","男",23,60);

        set.add(student1);
        set.add(student2);
        set.add(student3);
        set.add(student4);
        set.add(student5);

        // 遍历
        System.out.println(set.size());
        set.forEach(System.out::println);

    }
}
