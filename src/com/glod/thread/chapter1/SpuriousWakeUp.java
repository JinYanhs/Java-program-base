package com.glod.thread.chapter1;

import java.util.ArrayDeque;
import java.util.Queue;

/**
 * @description: 小例子探究虚假唤醒
 * @author: Glod
 * @date: 2021/1/25
 */
public class SpuriousWakeUp {
    private  Queue<String> queue;
    private final static int MAX_SIZE =  10;

    public SpuriousWakeUp(){
        queue = new ArrayDeque<>(MAX_SIZE);
    }

    // 生产者线程2
    private void producer(){
      synchronized (queue) {
          // 消费队列满，则等待队列空闲
          while(queue.size() == MAX_SIZE){
              // 挂起当前线程，并释放通过同步块获取的queue上的锁，让消费者线程
              // 可以获取队列里面的元素
              try {
                  // 阻塞并释放锁
                  queue.wait();
              } catch (InterruptedException e) {
                  e.printStackTrace();
              }
          }

          // 空闲则生成元素，并通知消费者线程
          System.out.println("\n------------生产--------------");
              queue.add("egg");
              System.out.println(Thread.currentThread()
                      + "生产了一枚鸡蛋，当前共有" + queue.size()
                      + "枚鸡蛋");
              // 类似wait方法，只有当前线程获取到了共享变量的监视器锁还后，才可
              // 调用共享变量的notify方法，否则抛出异常
              // 不同于在共享变量上调用notify函数会唤醒被阻塞到该共享变量上的
              // 一个线程，notifyAll方法则会唤醒所有在该共享变量上由于调用wait
               // 系列方法而被挂起的线程
              queue.notifyAll();


      }
    }

    //消费者线程
    private void consumer(){
       synchronized (queue){
           while(queue.size() == 0){
               try {
                   // 挂起当前线程，并释放通过同步块获取的queue上的锁，让生产者者线程
                   // 生产元素放入队列
                   queue.wait();
               } catch (InterruptedException e) {
                   e.printStackTrace();
               }
           }

           // 消费元素，并通知唤醒生产者
           System.out.println("\n------------消费--------------");
               queue.poll();
               System.out.println(Thread.currentThread()
                       + "取了了一枚鸡蛋，当前共有" + queue.size()
                       + "枚鸡蛋");
               queue.notifyAll();

       }


    }


    public static void main(String[] args) {
        SpuriousWakeUp swu = new SpuriousWakeUp();
        Thread producerThread = new Thread(()->{
            while(true) {
                try {
                    Thread.sleep((int)(Math.random() * 1000));
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                swu.producer();
            }
        },"producer1");

        Thread producerThread2 = new Thread(()->{
            while(true) {
                try {
                    Thread.sleep((int)(Math.random() * 1000));
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                swu.producer();
            }
        },"producer2");

        Thread consumerThread = new Thread(()->{
            while(true) {
                try {
                    Thread.sleep((int)(Math.random() * 1000));
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                swu.consumer();
            }

        },"consumer1");

        Thread consumerThread2 = new Thread(()->{
            while(true) {
                try {
                    Thread.sleep((int)(Math.random() * 1000));
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                swu.consumer();
            }

        },"consumer2");

        producerThread.start();
        consumerThread.start();
        producerThread2.start();
        consumerThread2.start();

        try {
            Thread.sleep(20000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }


}
