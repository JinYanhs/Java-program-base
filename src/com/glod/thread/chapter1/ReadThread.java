package com.glod.thread.chapter1;

/**
 * @description: 多线程下的指令重排-读线程
 * @author: Glod
 * @date: 2021/1/31
 */
public class ReadThread extends Thread{
    private static int num =0;
    private static boolean ready = false;
    public void run(){
        while(!Thread.currentThread().isInterrupted()){
            if (ready){  // 1
                System.out.println(num + num); // 2
            }
            System.out.println("read thread...");
        }
    }


    public static class WriteThread extends Thread{
        public void run(){

            num = 2; // 3
            ready = true; // 4

            System.out.println("writeThread set over...");
        }
    }


    public static void main(String[] args) {
        ReadThread rt = new ReadThread();
        rt.start();

        WriteThread wt = new WriteThread();
        wt.start();

        try {
            Thread.sleep(10);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        rt.interrupt();
        System.out.println("main exit");
    }

}
