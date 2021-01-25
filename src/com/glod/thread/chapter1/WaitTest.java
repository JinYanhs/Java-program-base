package com.glod.thread.chapter1;

import javax.annotation.Resource;

/**
 * @description: 当前线程调用共享标量的wait()方法后只会释放当前共享变量上的锁
 *                ，如果当前线程还持有其它共享变量上的锁，则这些锁是不会被释放的
 * @author: Glod
 * @date: 2021/1/25
 */
public class WaitTest {
    //创建资源
    private static volatile Object resourceA = new Object();
    private static volatile Object resourceB = new Object();

    public static void main(String[] args) throws InterruptedException {
        // 创建线程
        Thread threadA = new Thread(()->{
            // 获取resourceA共享资源的监视器锁
            synchronized (resourceA){
                System.out.println("ThreadA get resourceA lock");

                // 获取resourceB 共享资源的监视器锁
                synchronized (resourceB){
                    System.out.println("ThreadA get resourceB lock");
                    // 线程A阻塞，并释放获取到的resourceA的锁
                    System.out.println("ThreadA release resourceA lock");
                    try {
                        resourceA.wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        });

        // 创建线程
        Thread threadb = new Thread(()->{
            // 休眠1s
            try {
                Thread.sleep(1000);
                // 获取resourceA共享资源的监视器锁
                synchronized (resourceA){
                    System.out.println("threadB get resourceA lock");
                    System.out.println("threadB try get resourceB lock..");
                    // 获取resourceB共享资源的监视器锁
                    // 这个锁在threadA中未被释放（结局threadB阻塞）
                    synchronized (resourceB){
                        System.out.println("threadB get resourceB lock");
                        // 线程B阻塞，并释放获取到的resourceA的锁
                        System.out.println("ThreadB release resourceA lock");
                        resourceA.wait();
                    }
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });

        // 启动thread
        threadA.start();
        threadb.start();
        // 等待两个线程结束
        threadA.join();
        threadb.join();
        System.out.println("main over");

    }
}
