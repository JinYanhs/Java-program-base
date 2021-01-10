package com.glod.socket.networkProgramming.chapter3.mutithead2;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @description: JDK原生线程池来创建与客户端Socket交互
 * @author: 金研
 * @date: 2021/1/10
 */
public class EchoServer {
    private int port =8000;
    private ServerSocket serverSocket;
    private ExecutorService executorService; // 线程池
    private final int POOL_SIZE = 4; // 单个CPU时线程池中工作线程的数目 单CPU上轮询时间片执行/当一个线程IO时候其他线程抢占

    public EchoServer()throws IOException{
        serverSocket = new ServerSocket(port);
        // 创建线程池
        // Runtime的availableProcessors()方法返回当前系统的CPU的数目
        // 系统的CPU越多，线程池中工作线程的数目也越多
        // 该线程池中包含固定数目的线程，空闲线程会一直被保留。参数nThreads表示线程池中线程的数目
        executorService = Executors.newFixedThreadPool(Runtime.getRuntime().availableProcessors() * POOL_SIZE);
        System.out.println("当前主机上CPU数量：" + Runtime.getRuntime().availableProcessors());

        System.out.println("服务器启动");
    }

    public void service() {
        while (true) {
            Socket socket = null;
            try {
                socket = serverSocket.accept();
                executorService.execute(new Handler(socket));
            } catch (IOException e) {
                e.printStackTrace();
            }

        }
    }

    public static void main(String[] args)throws IOException {
        new EchoServer().service();
    }



}
