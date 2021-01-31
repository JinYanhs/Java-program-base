package com.glod.thread.chapter1;

/**
 * @description: 探究守护线程和用户线程
 * @author: Glod
 * @date: 2021/1/28
 */
public class DaemonThreadTest {
    /**
     * JVM发现没有用户线程了，就会终止JVM进程
     *
     */

    public static void main(String[] args) {
        Thread daemonThread = new Thread(new Runnable() {
            @Override
            public void run() {

            }
        });

        // 设置为守护线程
        daemonThread.setDaemon(true);
        daemonThread.start();

    }

    static class UserThreadTest{
        public static void main(String[] args) {
            Thread useerThread = new Thread(new Runnable() {
                @Override
                public void run() {
                    for (;;){

                    }
                }
            });
            /**
             *  父进程结束后，子线程还可以继续存在。子线程的生命周期并不受父进程的影响
             *  子线程存在的情况下JVM并不会终止
             */
            // 启动子线程
            useerThread.start();
            System.out.println("main is over");
        }
    }
}
