package com.glod.thread.chapter1;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @description: 线程在睡眠时拥有的监视器资源不会被释放
 * @author: Glod
 * @date: 2021/1/26
 */
public class SleepTest {
    /**
     * 线程睡眠时候，被其他线程中断会报中断异常
     */
    // 创建一个独占锁
    private static final Lock lock = new ReentrantLock();

    public static void main(String[] args) {
        // 创建线程A
        Thread threadA = new Thread(new Runnable() {
            @Override
            public void run() {
                // 获取独占锁
                lock.lock();
                System.out.println("子线程A在睡眠");
                try {
                    Thread.sleep(1000);
                    System.out.println("子线程A在唤醒");
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }finally {
                    // 释放锁
                    lock.unlock();
                }

            }
        });

        Thread threadB = new Thread(new Runnable() {
            @Override
            public void run() {
                /**
                 *  线程A获取了锁，会先输出一行，然后调用sleep方法让自己睡眠10s,在线程A睡眠的这
                 *  10s内那个独占锁lock还是线程A自己持有，线程B会一直阻塞到线程A醒来后执行unlock
                 *  释放锁
                 */
                // 获取独占锁
                lock.lock();
                System.out.println("子线程B在睡眠");
                try {
                    Thread.sleep(1000);
                    System.out.println("子线程B在唤醒");
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }finally {
                    // 释放锁
                    lock.unlock();
                }
            }
        });
        // 启动线程
        threadA.start();
        threadB.start();
    }
}
