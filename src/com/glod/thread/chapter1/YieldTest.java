package com.glod.thread.chapter1;

/**
 * @description: yield探究
 * @author: Glod
 * @date: 2021/1/26
 */
class YieldTest implements Runnable{

    /**
     * 当线程调用Thread类的静态方法yield时（让出CPU使用权，线程转为就绪状态，线程调度器
     * 会从线程就绪队列里面获取一个线程优先级最高的线程，也可能刚刚让出CPU执行权的），
     * 是在告诉线程调度器自己占有的时间片中
     * 还没有使用完的部分不想使用了，暗示线程调度器现在就可以进行下一轮的线程调度，
     * 只是让出自己剩余的时间片，并没有被阻塞挂起，而是处于就绪状态，下次有可能就会被调度执行
     */

    public YieldTest(){
        // 创建并启动线程
        Thread t = new Thread(this);
        t.start();
    }

    @Override
    public void run() {
        for (int i = 0; i < 5; i++){
            // 当i=0时，让出CPU执行权，放弃时间片，进行下一轮调度
            if ((i % 5) == 0){
                System.out.println(Thread.currentThread() + "yield cpu..");
                // 当前线程让出CPU执行权，放弃时间片，进行下一轮调度
                Thread.yield();
            }

        }
        System.out.println(Thread.currentThread() + "is over");
    }

    public static void main(String[] args) {

        /**
         * yield用于在调试或者测试时这个方法可以帮助复现由于并发竞争
         * 条件导致的问题，在设计并发控制时有用途
         */
        new YieldTest();
        new YieldTest();
        new YieldTest();
    }

}
