package com.glod.socket.networkProgramming.chapter2;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.Proxy;
import java.net.Socket;

/**
 * @description:
 * @author: 金研
 * @date: 2021/1/2
 */
public class ProxyTest<socket> {
    public static void main(String[] args) throws IOException {
        // 代理服务器地址
        String proxyIp = "myproxy.abc.com";
        // 代理服务器端口
        int proxyPort = 1080;
        /**
         *  Proxy.Type.SOCKS: 在分层的网络结构中，Type.SOCKS是位于会话层的代理类型。
         *  Proxy.Type.HTTP：在分层的网络结构中，Type.HTTP是位于应用层的代理类型。
         *  Proxy.Type.DIRECT:不使用代理,直接来凝结远程服务器
         */
        // 创建代理对象
        Proxy proxy = new Proxy(Proxy.Type.SOCKS,
                new InetSocketAddress(proxyIp,proxyPort));
        Socket socket = new Socket(proxy);
        //连接到远程服务器
        socket.connect(new InetSocketAddress("www.javathinker.net",80));
    }



}
