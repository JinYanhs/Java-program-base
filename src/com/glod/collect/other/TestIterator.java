package com.glod.collect.other;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * @description: 探究迭代器 iterator 可迭代 list & set
 *                  遍历中只可remove
 * @author: Glod
 * @date: 2021/3/8
 */
public class TestIterator {

    public static void main(String[] args) {
        List<Integer> list = new ArrayList<>();

        list.add(80);
        list.add(90);
        list.add(100);
        list.add(1,20);

        System.out.println(list);

        // iterator
        Iterator<Integer> it = list.iterator();
        while(it.hasNext()){
            Integer elem = it.next();
            if (elem.equals(100)){
               // list.remove(new Integer(100)); // 倒数第二个可以删，例外，除此之外报Exception in thread "main" java.util.
                                                 // ConcurrentModificationException
                it.remove(); //当前元素
            }
        }
        System.out.println(list);
    }


}
