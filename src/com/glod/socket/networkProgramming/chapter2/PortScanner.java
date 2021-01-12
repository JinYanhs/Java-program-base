package com.glod.socket.networkProgramming.chapter2;

import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.net.InetAddress;
import java.net.Socket;
import java.net.UnknownHostException;

/**
 * @description: PortScanner类可以扫描主机上从 1 到 1024之间的端口，判断这些端口是否已经被服务器程序监听。
 * @author: Glod
 * @date: 2021/1/2
 */
public class PortScanner {
    public static void main(String[] args) {
        String host = "localhost";
        if (args.length > 0){
            host = args[0];
        }
        new PortScanner().scan(host);
    }


    public void scan(String host) {
        Socket socket = null;
        for(int port = 1;port < 1024 ;port++){
            try {
                socket = new Socket(host,port);
                System.out.println("There is a server on port" + port);
            } catch (IOException e) {
                System.out.println("Cant connect to port" + port);
            }finally {
                if(socket != null){
                    try {
                        socket.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }

        }
    }

    /**
     *  InetAddress
     * @throws UnknownHostException
     */
    @Test
    public void  testInetAddress() throws UnknownHostException {
        // 返回主机的IP地址
        InetAddress addr1 = InetAddress.getLocalHost();
        System.out.println(addr1);

        //IP地址经常变化
        //返回代表“222.34.5.7”的IPv4地址
        InetAddress addr2 = InetAddress.getByName("222.34.5.7");
        System.out.println(addr2);

        //主机名不经常变化
        //返回主机名为“www.JavaThinker.net"的IP地址
        InetAddress addr4 = InetAddress.getByName("www.javathinker.net");
        System.out.println(addr4);
    }


}
