package com.glod.thread.chapter1;

/**
 * @description: 探究join方法
 * @author: Glod
 * @date: 2021/1/26
 */
public class JoinTest {

    /**
     *  线程A调用线程B的join方法后会被阻塞，当其他线程调用了线程A
     *  的interrupt方法中断了线程A时候，线程A会抛出InterruptedException
     *  异常而返回
     *
     */
    public static void main(String[] args) {
        // 线程1
        Thread thread1 = new Thread(new Runnable() {
            @Override
            public void run() {
                System.out.println("thread1 begin run!");
                for (;;){

                }
            }
        });

        // 获取主线程
        final Thread mainThread = Thread.currentThread();
        //线程2
        Thread thread2 = new Thread(new Runnable() {
            @Override
            public void run() {
                // 休眠1s
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                // 中断主线程
                mainThread.interrupt();
            }
        });

        // 启动子线程
        thread1.start();
        // 延迟1s启动线程
        thread2.start();
        try {
            // thread1线程在执行死循环，main调用thread1的join方法阻塞自己等待
            // 线程thread1执行完毕。待thread2休眠1s后会调用main的interrupt方法
            // 设置主线程的中断标志。从结果看main中的thread1.join处会抛出InterruptedException
            // 异常
            thread1.join();
        } catch (InterruptedException e) {
            System.out.println("main thread:" + e);
        }
    }
}
