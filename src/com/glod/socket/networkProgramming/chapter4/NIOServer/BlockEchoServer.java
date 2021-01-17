package com.glod.socket.networkProgramming.chapter4.NIOServer;


import com.glod.socket.networkProgramming.chapter3.closeServer.EchoServer;


import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @description: 阻塞的EchoServer
 * @author: Glod
 * @date: 2021/1/17
 */
public class BlockEchoServer {
    private int port = 8000;
    private ServerSocketChannel serverSockerChannel = null;
    private ExecutorService executorService; //线程池
    private static final int POOL_MULTIPLE = 4; //线程池中工作线程的数目

    public BlockEchoServer()throws IOException {
        // 创建一个线程池
        executorService = Executors.newFixedThreadPool(
                Runtime.getRuntime().availableProcessors() * POOL_MULTIPLE);
        // 创建一个ServerSocketChannel对象
        serverSockerChannel = ServerSocketChannel.open();
        // 使得在同一个主机上关闭了服务器程序，紧接着再启动该服务器程序时，可以顺利绑定上相同端口
        serverSockerChannel.socket().setReuseAddress(true);
        // 将服务器与本地端口绑定
        serverSockerChannel.socket().bind(new InetSocketAddress(port));
        System.out.println("服务器启动");
    }

    public void service(){
        while (true){
            SocketChannel socketChannel = null;
            try {
                socketChannel = serverSockerChannel.accept();
                // 处理客户连接
               // executorService.execute(new Handler(socketChannel));

            } catch (IOException e) {
                e.printStackTrace();
            }

        }
    }

    public static void main(String[] args) throws IOException{
        new EchoServer().service();

    }
}
