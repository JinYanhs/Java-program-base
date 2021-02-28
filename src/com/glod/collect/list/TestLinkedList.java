package com.glod.collect.list;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

/**
 * @description: 探究LinkedList
 * @author: Glod
 * @date: 2021/2/27
 */
public class TestLinkedList {
    /**
     * LinkedList: 循环？双向链表，实现了Deque（实现了Queue)接口->可以作为栈、队列来使用
     * 相比ArrayList增删不会有大量元素的移动
     *
     * ArrayList和LinkedList
     *  大量根据索引查询的操作，随机访问，大量的遍历操作（0-（n-1))，建议使用ArrayList
     *  如果存在较多的增加、删除操作，使用LinedList 》 空间换时间
     *
     *add(e)
     *  linkLast(E e)
     *    void linkLast(E e) {
     *         final Node<E> l = last; // 巧妙，第一次l必定为null,所以first指针永久指向first node 在代码里
     *         final Node<E> newNode = new Node<>(l, e, null);
     *         last = newNode;
     *         if (l == null)
     *             first = newNode;
     *         else
     *             l.next = newNode;
     *         size++;
     *         modCount++;
     *     }
     *
     *   first：永远指向第一个
     *   last: 永远指向最后一个
     */


    public static void main(String[] args) {
        LinkedList<Integer> linkedList = new LinkedList<Integer>();
        linkedList.add(80);
        linkedList.add(70);
        linkedList.add(90);

        linkedList.remove(2);
        linkedList.add(0,100);
        System.out.println(linkedList.size());
        System.out.println(linkedList.isEmpty());
        System.out.println(linkedList.indexOf(90));
        System.out.println(linkedList.contains(80));

        int ele = linkedList.get(2);
        System.out.println(ele);
        System.out.println(linkedList);
        Iterator it = linkedList.iterator();
        while (it.hasNext()){
            System.out.println(it.next());
        }

        /** LinkedList相比ArrayList提供了更多的方法**/
        linkedList.addFirst(20);
        linkedList.addLast(10);
        linkedList.getFirst();

        // 栈
        linkedList.peek();
        linkedList.pop();
        linkedList.push(22);
        System.out.println(linkedList);
    }
}
