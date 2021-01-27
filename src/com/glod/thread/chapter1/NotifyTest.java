package com.glod.thread.chapter1;

/**
 * @description: 线程A、B阻塞等待被C唤醒，且竞争锁
 *              A B 中必有一个最终阻塞
 * @author: Glod
 * @date: 2021/1/25
 */
public class NotifyTest {
    // 创建资源
    private static volatile Object resourceA = new Object();

    public static void main(String[] args) throws InterruptedException {
        // 创建线程A
        Thread threadA = new Thread(new Runnable() {
            @Override
            public void run() {
                // 获取resourceA共享资源的监视器锁
                synchronized (resourceA){
                    System.out.println("ThreadA get resourceA lock");
                    System.out.println("ThreadA begin wait");
                    try {
                        resourceA.wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    System.out.println("ThreadA end wait");
                }
            }
        });

        // 创建线程B
        Thread threadB = new Thread(new Runnable() {
            @Override
            public void run() {
                // 获取resourceB共享资源的监视器锁
                synchronized (resourceA){
                    System.out.println("ThreadB get resourceA lock");
                    System.out.println("ThreadB begin wait");
                    try {
                        resourceA.wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    System.out.println("ThreadB end wait");
                }
            }
        });

        // 创建线程C
        Thread threadC = new Thread(new Runnable() {
            @Override
            public void run() {
                synchronized (resourceA){
                    System.out.println("ThreadC begin notify");

                    resourceA.notify();
                }
            }
        });

        // 启动
        threadA.start();
        threadB.start();
        Thread.sleep(1000);
        threadC.start();
        threadA.join();
        threadB.join();
        threadC.join();
        System.out.println("main over");

    }
}
