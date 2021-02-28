package com.glod.collect.set;

import java.util.Comparator;

/**
 * @description: 实现Comparator接口比较 （相比Comparable在外部）
 * @author: Glod
 * @date: 2021/2/28
 */
public class StuScoreDescComparator implements Comparator<Student> {
    @Override
    public int compare(Student o1, Student o2) {
        if (o1.getScore() > o2.getScore()){
            return 1;
        }else if (o1.getScore() < o2.getScore()){
            return -1;
        }
        // return 0;
        // 一样再比姓名
        return -o1.getName().compareTo(o2.getName());
    }
}
