package com.glod.collect.map;

import java.util.*;

/**
 * @description: 探究Map
 * @author: Glod
 * @date: 2021/2/28
 */
public class TestMap {
    /**
     * Map:
     *  adv: 存储键值对映射关系，根据key可以找到value
     * HashMap：采用Hashtable哈希表存储结构
     *   key: 无序 唯一 HashSet
     *   value: 无序 不唯一 Collection
     *  adv:添加速度快，查询速度快，删除速度快
     *  disadv:key无序
     *
     * LinkedHashMap:采用哈希表存储结构，同时使用链表维护次序
     *  key有序（添加顺序）唯一 LinkedHashSet
     *  value:无序 不唯一 Collection
     *
     * TreeMap: 红黑树
     *  key:有序（自然顺序） 唯一 TreeSet
     *  value:无序，不唯一 Collection
     *
     *  Set和Map的关系
     *    Map中只存储key,不存value,就是Set
     */

    public static void main(String[] args) {
//        Map<String,String> map = new HashMap<String,String>();
//        Map<String,String> map = new LinkedHashMap<String,String>();
        Map<String,String> map = new TreeMap<String,String>();

        map.put("cn","china");
        map.put("jp","Japan");
        map.put("us","the United States");
        map.put("us","America");  // 覆盖掉旧的value
        map.put("uk","the United Kingdom");
        map.put("en","the United Kingdom");

        System.out.println(map.size());
        System.out.println(map);
        System.out.println(map.get("us"));

        System.out.println(map.keySet()); // 所有key Set类型
        System.out.println(map.values()); // 所有的value Collection类型

        // 遍历Map
        //方式1：得到所有的key组成的Set
        Set<String> keySet = map.keySet();
        for (String key : keySet){
            System.out.println(key + "--------->" + map.get(key));
        }

        //方式2：得到所有Entry(就是一个哈希表或红黑树中节点类）组成的Set集合
        Set<Map.Entry<String, String>> entries = map.entrySet();
        Iterator<Map.Entry<String, String>> iterator = entries.iterator();
        while (iterator.hasNext()){
            Map.Entry<String, String> next = iterator.next();
            System.out.println(next.getKey()+ ":" + next.getValue());

        }



    }
}
