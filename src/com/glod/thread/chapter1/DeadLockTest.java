package com.glod.thread.chapter1;

/**
 * @description: 探究死锁
 * @author: Glod
 * @date: 2021/1/27
 */
public class DeadLockTest {

    /**
     *  死锁的四个条件
     *
     *   互斥 （不可破）
     *   请求并持有 （可破）
     *   资源的不可剥夺 （不可破）
     *   环路等待  （可破）
     */
    // 资源
    private static Object resourceA = new Object();
    private static Object resourceB = new Object();

    public static void main(String[] args) {
        // 创建线程A
        Thread threadA = new Thread(new Runnable() {
            @Override
            public void run() {
                synchronized (resourceA){
                    System.out.println(Thread.currentThread() + "get ResourceA");
                    try {
                        // 为了保证线程A在获取resourceB对应的锁前让线程B抢占到CPU,获取到资源resourceB上的锁
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    System.out.println(Thread.currentThread() + "waiting get resourceB");
                    synchronized (resourceB){
                        System.out.println(Thread.currentThread() + "get resourceB");
                    }
                }
            }
        });

        // 创建线程B
        Thread threadB = new Thread(new Runnable() {
            @Override
            public void run() {
                synchronized (resourceB){
                    System.out.println(Thread.currentThread() + "get resourceB");

                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }

                    System.out.println(Thread.currentThread() + "waiting get resourceA");
                    synchronized (resourceA){
                        System.out.println(Thread.currentThread() + "get resourceA");
                    }
                }
            }
        });

        // 启动
        threadA.start();
        threadB.start();
    }
}
