package com.glod.thread;

import java.util.LinkedList;
import java.util.Queue;

/**
 * @description 共享空间 车库
 */
public class Carbarn {
    Queue<Carport> carportQueue = new LinkedList<>();
    int index = 6;

    /**
     *
     * @function 生产方法
     */

    public synchronized void carOut(Carport c){
        try {
            while (index == carportQueue.size()) {
                System.out.println("车库车位超级充足！");
                this.wait();
            }
            this.notify();
        }catch(InterruptedException e){
            e.printStackTrace();
        }catch (IllegalMonitorStateException e){
            e.printStackTrace();
        }

        //
        carportQueue.offer(c);
        System.out.println("****************生产——驶出*********************");
        System.out.println("当前有一个空余车位产生了，当前车库共有： " + carportQueue.size() + "个车位！");
    }

    /**
     * @function 消费方法
     */
    public synchronized Carport carIn() {
        try {
        while (carportQueue.size() == 0){
            System.out.println("老板！！！你家车库满了！");
                this.wait();
        }
        this.notify();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }catch (IllegalMonitorStateException e){
            e.printStackTrace();
        }
         Carport c = carportQueue.poll();
        System.out.println("——————————消费——停入——————————");
        System.out.println("有车停在车库了，这是处理的第" + c + "，当前车库有： " + carportQueue.size() + "车位!");
        return c;
    }
}
