package com.glod.generics;

import com.glod.thread.chapter1.ForContentTest;

/**
 * @Description 泛型方法和可变参数
 *
 * @Author glod
 * @Date 2021/5/7
 * @Modifier
 * @Modified Date
 * @Version 1.0
 */
public class GenericMethodAndArgsTest {
    public static <T> void printMsg(T... args){
        for (T t : args){
            System.out.println("泛型测试，可变参数 t is " + t);
        }
    }

    public static void main(String[] args) {
        //printMsg(1,2,3,4,5, 6);
       // printMsg("我","们","都","是","木","头","人");
        printMsg("111",222,"aaaa","2323.4",55.55);
    }
}
