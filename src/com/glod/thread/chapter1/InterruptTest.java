package com.glod.thread.chapter1;

/**
 * @description: 探究中断
 * @author: Glod
 * @date: 2021/1/26
 */
public class InterruptTest {
    /**
     * interrupt() :若线程调用了wait()、join()或者sleep()方法而被挂起，
     *   这时候其他线程调用该线程interrupt方法会抛出InterruptedException
     *
     * isInterrupted()
     */

    //根据中断标志判断线程是否终止
    public static void main(String[] args) {
        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                // 如果当前线程被中断则退出循环
                while (!Thread.currentThread().isInterrupted()){
                    System.out.println(Thread.currentThread() + "你好");
                }
            }
        });

        // 启动子线程
        thread.start();
        // 主线程休眠1s,以便中断前让子线程输出
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        // 中断子线程
        System.out.println("main中断子线程 [我不好，再见啊]");
        thread.interrupt();
        //thread.interrupt();
        // 等待子线程执行完毕
        try {
            thread.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("main over");
    }
}
