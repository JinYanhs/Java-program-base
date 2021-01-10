package com.glod.socket.networkProgramming.chapter3;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * @description: 服务器端进程
 * @author: 金研
 * @date: 2021/1/4
 */
public class Server {
    /**
     *  请求队列最大最小区间 0-50
     */
    private int port = 8000;
    private ServerSocket serverSocket;

    public Server()throws IOException{
        // 请求连接队列为3
        serverSocket = new ServerSocket(port,100);
        System.out.println("服务器启动");
    }

    public void service(){
        while(true){
            Socket socket = null;

                try {
                    socket = serverSocket.accept(); // 从请求队列中取出一个连接
                    System.out.println("New connection accepted"
                            + socket.getInetAddress() + ":" + socket.getPort());
                } catch (IOException e) {
                    e.printStackTrace();
                }finally {
                    if (socket != null){
                        try {
                            socket.close();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                }

        }
    }

    public static void main(String[] args) throws Exception{
        Server server = new Server();
        Thread.sleep(60000 * 10); // 睡眠10分钟
        server.service(); // 注释掉这行代码永远不会执行serverSocket.accept()方法，这意味着队列中的连接请求永远不会被取出

    }
}
