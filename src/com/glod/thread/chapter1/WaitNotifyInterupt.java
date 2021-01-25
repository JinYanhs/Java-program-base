package com.glod.thread.chapter1;

/**
 * @description: 当一线程调用共享对象的wait()方法
 *      被阻塞挂起后，如果其他线程中断了该线程，则该线程会抛出InterruptedException异常并返回
 * @author: Glod
 * @date: 2021/1/25
 */
public class WaitNotifyInterupt {
    static Object obj = new Object();
    public static void main(String[] args) throws InterruptedException {
        // 创建线程
        Thread threadA = new Thread(new Runnable() {
            @Override
            public void run() {
                System.out.println("=======开始===========");
                // 阻塞当前线程
                synchronized (obj){
                    try {
                        obj.wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                System.out.println("==========结束=================");
            }
        });

        threadA.start();
        Thread.sleep(1000);
        System.out.println("----------begin interrupt threadA-----------");
        threadA.interrupt();
        System.out.println("----------end interrupt threadA------------");
    }
}
