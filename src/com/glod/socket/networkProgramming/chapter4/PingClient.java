package com.glod.socket.networkProgramming.chapter4;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.Buffer;
import java.nio.channels.AsynchronousSocketChannel;
import java.util.LinkedList;
import java.util.concurrent.*;

/**
 * @description: 异步通道和异步运算（AsynchronousSocketChannel AsynchronousServerSocketChannel）
 *              异步通道的一些方法总是采用非阻塞模式，并且他们的非阻塞方法会立即返回一个Future对象，用来存放方法的异步运算结果
 * @author: Glod
 * @date: 2021/1/23
 */
public class PingClient {
    // 存放所有PingResult结果的队列
    private LinkedList<PingResult> pingResults = new LinkedList<PingResult>();
    boolean shutdown = false;
    ExecutorService executorService;
    private final int POOLSIZE = 4;

    public PingClient(){
        executorService = Executors.newFixedThreadPool(Runtime.getRuntime().availableProcessors() * POOLSIZE);
        executorService.execute(new Printer() );
        receivePingAddress();
    }

    /** 接收用户输入的主机地址，由线程池执行PingHandler任务 */
    public void receivePingAddress(){
        try {
            BufferedReader localReader = new BufferedReader(new InputStreamReader(System.in));
            String msg = null;
            // 接收用户输入的主机地址
            while ((msg = localReader.readLine()) != null) {
                if ("bye".equals(msg)){
                    shutdown = true;
                    executorService.shutdown();
                    break;
                }
                executorService.execute(new PingHandler(msg));


            }
        }catch (IOException e){}

    }

    public static void main(String[] args) {
        new PingClient();
    }

    /** 尝试连接特定主机，并且把运算结果加入PingResults结果队列中*/
    public void addPingResult(PingResult pingResult){
        AsynchronousSocketChannel socketChannel = null;
        try {
            socketChannel = AsynchronousSocketChannel.open();
            pingResult.socketChannel = socketChannel;
            pingResult.connectStart = System.currentTimeMillis();

            synchronized (pingResults){
                // 向pingResults队列中接入一个PingResult对象
                pingResults.add(pingResult);
                 pingResults.notify();
            }

            Future<Void> connectResult = socketChannel.connect(pingResult.address);
            pingResult.connectResult = connectResult;

        } catch (IOException e) {
             if (socketChannel != null){
                 try {
                     socketChannel.close();
                 } catch (IOException ex) {
                     ex.printStackTrace();
                 }
             }

             pingResult.failure  = pingResult.ERROR;

        }
    }

    /** 打印PingResults结果队列中已经执行完毕的任务结果 */
    public void printPingResult(){
        PingResult pingResult = null;
        while(!shutdown){
            synchronized (pingResults){
                while(!shutdown && pingResults.size() == 0){
                    try {
                        pingResults.wait(100);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    // 原程序上bug?
                    if (!shutdown && pingResults.size() == 0){
                        break;
                    }

                    pingResult = pingResults.getFirst();

                    if (pingResult.connectResult != null){
                        try {
                            pingResult.connectResult.get(500, TimeUnit.MILLISECONDS);
                        } catch (Exception e) {
                           pingResult.failure = pingResult.ERROR;

                        }

                    }

                    if (pingResult.connectResult != null
                            && pingResult.connectResult.isDone() ){
                        pingResult.connectFinish  = System.currentTimeMillis();
                    }

                    if (pingResult.connectResult != null
                            && pingResult.connectResult.isDone()
                            || pingResult.failure != null){

                        pingResult.print();
                        pingResults.removeFirst();
                        try {
                            pingResult.socketChannel.close();
                        }catch (IOException e){}
                    }
                }
            }
        }

    }

    /** 尝试连接特定主机，生成一个PingResult对象，把它加入PingResults结果队列中 */
    public class PingHandler implements Runnable{
        String msg;
        public PingHandler(String msg){
            this.msg = msg;
        }
        @Override
        public void run() {
            if (!"bye".equals(msg)){
                PingResult pingResult = new PingResult(msg);
                addPingResult(pingResult);
            }
        }
    }

    /** 打印PingResults结果队列中已经执行完毕的任务结果 */
    public class Printer implements Runnable{
        @Override
        public void run() {
               printPingResult();
            }
        }
    }

