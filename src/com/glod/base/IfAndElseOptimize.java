package com.glod.base;

import org.junit.jupiter.api.Test;

import java.util.*;
import java.util.function.Consumer;
import java.util.stream.Stream;

/**
 * @Description if\else优化,判断奇数偶数
 *
 * @Author glod
 * @Date 2021/5/7 
 * @Modifier
 * @Modified Date
 * @Version 1.0
 */
public class IfAndElseOptimize {
    List<Integer> ints =Arrays.asList(1,2,3,4,5,6,7,8,9,10);
    @Test
    public void rawStyle(){

        ints.stream().forEach(v->{
            if (v.intValue() % 2 == 0) {System.out.print(v + " is even; ");}
            else{
                System.out.println(v +  " is odd; ");
            }
        });
    }

    @Test
    public void optimizeVersion1(){
        Stream<Integer> evenInteger = ints.stream().filter(v->v.intValue() % 2 == 0);
        Stream<Integer> oddInteger = ints.stream().filter(v->v.intValue() % 2 != 0);

        evenInteger.forEach(v->System.out.print(v + " is even; "));
        oddInteger.forEach(v->System.out.print(v + " is odd; "));
    }

    @Test
    public void optimizeVersion2(){
        Map<Boolean,Consumer> map = new HashMap();

        Consumer consumer = c-> System.out.println(c + " is even;");
        Consumer consumer1 = c-> System.out.println(c + " is odd;");

        ints.stream().forEach(v->{
            map.put(true,consumer);
            map.put(false,consumer1);

            map.get(v.intValue() % 2 ==0).accept(v);
        });
    }
}
