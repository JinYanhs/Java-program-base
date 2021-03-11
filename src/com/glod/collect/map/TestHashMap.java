package com.glod.collect.map;

import java.util.HashMap;

/**
 * @description: 探究JavaHashtable
 * @author: Glod
 * @date: 2021/2/28
 */
public class TestHashMap {
    public static void main(String[] args) {

        /**
         * 链表大于八转为树，小于6退化成链表
         */
        // HashMap map = new HashMap(16,0.75f); // default setting
        //HashMap map = new HashMap(5000,0.5f); // 预估合理容量，避免频繁扩容 0.5f:碰撞和扩容的妥协
        HashMap hashMap = new HashMap(20);
    }
}
