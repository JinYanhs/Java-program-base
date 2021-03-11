package com.glod.collect.other;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;

/**
 * @description: 探究迭代器 ListIterator 只能迭代list
 *                  遍历中可以remove() add() set()
 *                  可以定位到当前的索引位置
 * @author: Glod
 * @date: 2021/3/8
 */
public class TestListIterator {

    public static void main(String[] args) {
        List<Integer> list = new ArrayList<>();

        list.add(80);
        list.add(90);
        list.add(100);
        list.add(1,20);

        System.out.println(list);

        // iterator
        ListIterator<Integer> it = list.listIterator();
        // 从前向后遍历 cursor 0 每次调用 it.next() cursor + 1  lastRet
        while(it.hasNext()){
             Integer elem = it.next();
//            if (elem.equals(100)){
//               // list.remove(new Integer(100)); // 倒数第二个可以删，例外，除此之外报Exception in thread "main" java.util.
//                                                                // ConcurrentModificationException
//               // it.remove(); //当前元素
//            }
            System.out.println(elem + " " + it.previousIndex() + " "  + it.nextIndex());
            System.out.println();
        }
        System.out.println("====================");
        // 从后向前遍历 cursor -1  先得从前往后遍历才能从后往前遍历
        while(it.hasPrevious()){
            System.out.println(it.previous());
        }

        System.out.println(list);
    }


}
