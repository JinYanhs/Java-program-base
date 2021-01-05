package com.glod.socket.networkProgramming.chapter3.mutithread1;



import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;


/**
 * @description: 为每个客户分配一个新的工作线程，当工作线程与客户端通信结束时，这个线程就被销毁。
 * @author: 金研
 * @date: 2021/1/5
 */
public class EchoServer {
    private int port = 8000;
    private ServerSocket serverSocket;

    public EchoServer()throws IOException{
        serverSocket = new ServerSocket(port);
        System.out.println("服务器启动");
    }

    public void service() {
        while(true){
            Socket socket = null;
            try {
                socket = serverSocket.accept(); // 接收客户连接
                // 创建一个工作线程
                Thread workThread = new Thread(new Handler(socket));
                workThread.start(); // 启动工作线程
            }catch (IOException e){
                e.printStackTrace();
            }
        }
    }

    public static void main(String[] args) throws Exception {
        new EchoServer().service();
    }
}
