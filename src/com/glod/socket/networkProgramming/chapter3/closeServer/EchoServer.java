package com.glod.socket.networkProgramming.chapter3.closeServer;

import com.glod.socket.networkProgramming.chapter3.mutithead2.Handler;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketException;
import java.net.SocketTimeoutException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.RejectedExecutionException;
import java.util.concurrent.TimeUnit;

/**
 * @description: 此服务器可以关闭自身，而不依靠操作系统强行终止.
 * @author: Glod
 * @date: 2021/1/11
 */
public class EchoServer {
    private int port = 8000;
    private ServerSocket serverSocket;
    private ExecutorService executorService; // 线程池
    private final int POOL_SIZE = 4; // 单个CPU时线程池中工作线程的数目

    private int portForShutdown = 8001; // 用于监听关闭服务器命令的端口
    private ServerSocket serverSOcketForShutdown;
    private boolean isShutdown = false; // 服务器是否已经关闭

    private Thread shutdownThread = new Thread() { // 负责关闭服务器的线程
        public void run() {
            Socket socketForShutdown = null;

            try {
                socketForShutdown = serverSOcketForShutdown.accept();
                BufferedReader br = new BufferedReader(
                        new InputStreamReader(socketForShutdown.getInputStream())
                );
                String command = br.readLine();
                if ("close".equals(command.trim())) {
                    long beginTime = System.currentTimeMillis();
                    socketForShutdown.getOutputStream()
                            .write("服务器正在关闭\r\n".getBytes());
                    isShutdown = true;

                    // 请求关闭线程池
                    // 线程池不再接收新的任务，但是会继续执行完工作队列中现有的任务
                    executorService.shutdown();

                    // 等待关闭线程池，每次等待的超时时间为30s
                    while (!executorService.isTerminated()) {
                        try {
                            executorService.awaitTermination(30, TimeUnit.SECONDS);
                            // 关闭与EchoClient客户通信的ServerSocket
                            serverSocket.close();
                            long endTime = System.currentTimeMillis();
                            socketForShutdown.getOutputStream().write(("服务器已经关闭，关闭服务器用了"
                                    + (endTime - beginTime) + "ms\r\n").getBytes());
                            serverSOcketForShutdown.close();
                            System.out.println("服务器关闭");
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }

                    }
                } else {
                    socketForShutdown.getOutputStream()
                            .write("错误的命令\r\n".getBytes());
                    socketForShutdown.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    };

    public EchoServer() throws IOException {
        serverSocket = new ServerSocket(port);

        // 设定等待客户连接的超过时间为60s
        serverSocket.setSoTimeout(60000);
        serverSOcketForShutdown = new ServerSocket(portForShutdown);

        // 创建线程池
        executorService = Executors.newFixedThreadPool(
                Runtime.getRuntime().availableProcessors() * POOL_SIZE);

        shutdownThread.start(); // 启动负责关闭服务器的线程
        System.out.println("服务器启动");
    }

    public void service() {
        while (!isShutdown) {
            Socket socket = null;
            try {
                socket = serverSocket.accept();
                // 把等待酷虎发送数据的超时时间设为60s
                socket.setSoTimeout(60000);
                // 可能会抛出RejectedExecutionException
                executorService.execute(new Handler(socket));
            } catch (SocketTimeoutException e) {
                // 不必处理等待客户连接时出现超时异常
            } catch (RejectedExecutionException e) {

                if (socket != null) {
                    try {
                        socket.close();
                    } catch (IOException ex) {
                        return;
                    }
                }

            } catch (SocketException e) {
                if (e.getMessage().indexOf("socket closed") != -1){
                    return;
                }
            }catch (IOException e){
                e.printStackTrace();
            }
        }
    }

    public static void main(String[] args) throws IOException {
        new EchoServer().service();
    }

}
