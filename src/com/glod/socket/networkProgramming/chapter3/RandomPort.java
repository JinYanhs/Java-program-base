package com.glod.socket.networkProgramming.chapter3;

import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.net.ServerSocket;

/**
 * @description: 端口扫描&匿名端口
 * @author: 金研
 * @date: 2021/1/5
 */
public class RandomPort {
    /**
     *  构造ServerSocket时候，如果把端口设为0，那么将由操作系统为服务器分配一个端口(称为匿名端口)，程序只要调用getLocalPort()方法就能获知这个端口号。
     */
    @Test
    private void anonymityPort()throws IOException{
        ServerSocket serverSOcket = new ServerSocket(0);
        System.out.println("系统分配的监听端口为：" + serverSOcket.getLocalPort());
    }

    /**
     *  如果希望能够释放服务器端口，以便让其他程序能占用该端口，则可以显示地调用ServerSocket的close()方法。
     *  function: 以下代码用于扫描1~65535之间的端口号。
     */
    @Test
    private void scanport(){

        for (int port = 1; port <= 65535; port++){
            try {
                ServerSocket serverSocket = new ServerSocket(port);
                serverSocket.close(); // 及时关闭ServerSocket
            }catch (IOException e){
                System.out.println("端口" + port + "已经被其他服务进程占用");
            }
        }
    }
}
