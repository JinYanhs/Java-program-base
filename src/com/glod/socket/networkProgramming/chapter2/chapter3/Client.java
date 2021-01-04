package com.glod.socket.networkProgramming.chapter2.chapter3;

import java.net.Socket;

/**
 * @description: 客户端进程
 * @author: 金研
 * @date: 2021/1/4
 */
public class Client {
    public static void main(String[] args) throws Exception{
        final int length = 100;
        String host = "localhost";
        int port = 8000;

        Socket[] sockets = new Socket[length];
        //试图建立100次连接
        for (int i = 0; i < length; i++){
            sockets[i] = new Socket(host,port);
            System.out.println("第" + (i + 1) + "次连接成功");
        }
        Thread.sleep(3000);
        for (int i = 0; i < length; i++){
            sockets[i].close();
        }
    }
}
