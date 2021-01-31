package com.glod.thread.chapter1;

import java.util.HashMap;

/**
 * @description: 探究ThreadLocal
 * @author: Glod
 * @date: 2021/1/28
 */
public class ThreadLocalTest {

    /**  res one:
     *   thread one:我是一号线程的本地变量
     *   thread one remove after:null
     *   thread two:我是二号线程的本地变量
     *   thread two remove after:null
     *
     *
     *   res two
     *
     *   thread two:我是二号线程的本地变量
     *   thread one:我是一号线程的本地变量
     *   thread two remove after:null
     *   thread one remove after:null
     *
     *
     *
     *
     */

    private static ThreadLocal<String> threadLocalVar = new ThreadLocal<>();

    private  static void  print(String str){
        // 打印线程本地内存中变量threadLocalVar的值
        System.out.println(str + ":" + threadLocalVar.get());
        // 清除当前线程本地内存中的变量threadLocalVar
        threadLocalVar.remove();
    }

    public static void main(String[] args) {
        // 一号线程
        Thread thread1 = new Thread(new Runnable() {
            @Override
            public void run() {
                // 设置的是线程one本地内存的一个副本,这个副本线程TWO是访问不了的
                threadLocalVar.set("我是一号线程的本地变量");

                print("thread one");

                System.out.println("thread one remove after" + ":" + threadLocalVar.get());
            }
        });

        // 二号线程
        Thread thread2 = new Thread(new Runnable() {
            @Override
            public void run() {
                threadLocalVar.set("我是二号线程的本地变量");

                print("thread two");

                System.out.println("thread two remove after" + ":" + threadLocalVar.get());
            }
        });


        thread1.start();
        thread2.start();
    }
}
