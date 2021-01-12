package com.glod.socket.networkProgramming.chapter3;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * @description: 单线程服务器采用的通信流程
 * @author: Glod
 * @date: 2021/1/5
 */
public class SingleThreadServer {
    /**
     * 当服务器正在进行发送数据的操作时候，如果客户端断开了连接，那么服务器端会抛出一个IOException的子类SocketException
     */
    private int port = 8000;
    private ServerSocket serverSocket;

    public SingleThreadServer()throws IOException {
        serverSocket = new ServerSocket(port,3);
    }

    public void service()throws IOException{
        while(true){
            Socket socket = null;
            try{
                socket = serverSocket.accept(); // 从连接请求队列中取出一个连接
                System.out.println("new connection accepted"
                       + socket.getInetAddress() + ":" + socket.getPort());
                // 接发数据

            }catch (IOException e){
                // 这只是与单个客户通信时遇到的异常，可能是由于客户端过早断开连接引起的
                // 这种异常不应该中断整个while循环
                e.printStackTrace();
            }finally {
                // 同一个客户通信后结束后，要关闭Socket
                if (socket != null){
                    socket.close();
                }


            }
        }
    }

    public static void main(String[] args) throws IOException{
        SingleThreadServer singleThreadServer = new SingleThreadServer();
        singleThreadServer.service();
    }
}
