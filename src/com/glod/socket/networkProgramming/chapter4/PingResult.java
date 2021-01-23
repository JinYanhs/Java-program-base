package com.glod.socket.networkProgramming.chapter4;

import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.UnknownHostException;
import java.nio.channels.AsynchronousSocketChannel;
import java.util.concurrent.Future;

/**
 * @description: 表示连接一个主机的结果
 * @author: Glod
 * @date: 2021/1/22
 */
public class PingResult {
    InetSocketAddress address;
    // 开始连接时间的时间
    long connectStart;
    // 连接成功时的时间
    long connectFinish = 0;
    String failure;
    // 连接操作的异步运算结果
    Future<Void> connectResult;
    AsynchronousSocketChannel socketChannel;
    String host;
    final String ERROR = "连接失败";

    PingResult(String host){

        try {
            this.host = host;
            // 不连接网络，这块会有NP异常
            address = new InetSocketAddress(InetAddress.getByName(host),80);
        } catch (UnknownHostException e) {
            e.printStackTrace();
            failure = ERROR;
        }
    }

    public void print(){// 打印连接一个主机的执行结果
        String result;
        if (connectFinish != 0){
            result = Long.toString(connectFinish - connectStart) + "ms";
        }else if(failure != null){
            result = failure;
        }else{
            result = "Timed out";

        }
        System.out.println("ping " + host + "的结果" + ":" + result);
    }

}
