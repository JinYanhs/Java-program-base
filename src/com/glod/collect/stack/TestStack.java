package com.glod.collect.stack;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.Stack;

/**
 * @description: 探究栈
 * @author: Glod
 * @date: 2021/2/27
 */
public class TestStack {
    /**
     *  Stack继承至Vector
     *   Vector已经被ArrayLit替代了，Stack也不建议使用了
     *
     *   Deque:
     *      ArrayDeque : 底层使用数组
     *      LinkedList  ： 底层使用双向链表
     *
     *    队列接口Queue
     *     ArrayQueue : 底层使用数组
     *     LinkedList ： 底层使用双向链表
     *
     *
     */
    Stack stack = new Stack(); // 不推荐

    Deque deque = new ArrayDeque(); // 双端队列

    public static void main(String[] args) {
        TestStack testStack = new TestStack();
        Deque<String> deque = new ArrayDeque();

        deque.push("一队");
        deque.push("二队");
        deque.push("三队");

        String elem = deque.peek();
        System.out.println(elem);
        System.out.println(deque.size());
        while (!deque.isEmpty()){
            String elem2 = deque.pop();
            System.out.println(elem2);
        }
    }

}
