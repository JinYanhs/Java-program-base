package com.glod.thread.chapter1;

/**
 * @description: interrupt探究2
 * @author: Glod
 * @date: 2021/1/26
 */
public class InterruptTest2 {

    public static void main(String[] args) {
        Thread thread1 = new Thread(new Runnable() {
            @Override
            public void run() {
                for (;;){

                }
            }
        });

        // 启动线程
        thread1.start();
        // 设置中断标志
        thread1.interrupt();
        // 获取中断标志
        System.out.println("isInterrupted :" + thread1.isInterrupted());
        // 获取中断标志并重置
        System.out.println("isInterrupted :" + thread1.interrupted());
        // 获取中断标志并重置
        System.out.println("isInterrupted :" + Thread.interrupted());
        // 获取中断标志
        System.out.println("isInterrupted :" + thread1.isInterrupted());
        try {
            thread1.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("main is over");
    }
}
