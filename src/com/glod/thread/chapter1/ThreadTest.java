package com.glod.thread.chapter1;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;

/**
 * @description: 多线程实现
 * @author: Glod
 * @date: 2021/1/25
 */
public class ThreadTest {

    // 继承Thread类并重写run方法
    public static class MyThread extends Thread{
        @Override
        public void run(){
            // 继承方式好处：在run()方法内获取当前线程直接使用this就可以了
            // 无须使用Thread.currentThread()方法
            // 坏处：单继承，任务与代码没有分离，多个线程执行一样的任务时候需
            // 多份任务代码
            System.out.println(this.getName() + " : 这是一个Thread子线程");
        }
                                   // 3.终止状态（run方法执行完毕）
    }

    // 实现Runnable接口
    public static class RunnableTask implements Runnable{

        @Override
        public void run() {
            System.out.println(Thread.currentThread() + ": 这是一个Runnable子线程");
        }
    }

    // 实现Callable接口
    public static class CallerTask implements Callable<String>{

        @Override
        public String call() throws Exception {
            return Thread.currentThread() + "：这是一个Callable子线程";
        }
    }

    public static void main(String[] args) {
        System.out.println("--这是Dad-->" + Thread.currentThread());
        // 继承Thread创建线程
        MyThread thread = new MyThread();
        // 启动线程
        thread.start(); //启动线程 ->1.就绪状态（获取了出了CPU资源外的其他资源）
                        //          2.运行状态（获取了CPU的执行权）
        // thread.start(); // 线程启动两次 java.lang.IllegalThreadStateException

        RunnableTask runnableTask = new RunnableTask();
        // 两个Thread共用一个runnableTask代码逻辑
        new Thread(runnableTask).start();
        new Thread(runnableTask).start(); // 正常运行

        // 创建异步任务
        FutureTask<String>  futureTask = new FutureTask<>(new CallerTask());
        new Thread(futureTask).start();
        try {
            System.out.println("Caller返回结果: " + futureTask.get());
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
    }
}
