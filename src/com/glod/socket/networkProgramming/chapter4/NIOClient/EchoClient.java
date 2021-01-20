package com.glod.socket.networkProgramming.chapter4.NIOClient;

import com.glod.socket.networkProgramming.chapter3.closeServer.EchoServer;

import java.io.*;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.nio.Buffer;
import java.nio.channels.SocketChannel;

/**
 * @description: 阻塞式的客户端（服务器端一般不需要同时建立与服务器的多个连接，因而一个线程阻塞模式运行就可满足）
 *               同步通信：A向B发送了一批数据后，必须等接收到了B的响应数据后，再发送下一批数据
 * @author: Glod
 * @date: 2021/1/20
 */
public class EchoClient {
    private SocketChannel socketChannel = null;

    public EchoClient() throws IOException {
        socketChannel = SocketChannel.open();
        // InetAddressz（主机）  InetSocketAddress（主机 + 端口号）
        InetAddress ia = InetAddress.getLocalHost();
        InetSocketAddress isa = new InetSocketAddress(ia,8000);
        // 阻塞式运行，将等到与远程服务器的连接建立成功才会返回
        socketChannel.connect(isa);
        System.out.println("与服务器建立连接成功");
    }


    private PrintWriter getWriter(Socket socket) throws IOException {
        return new PrintWriter(new OutputStreamWriter(socket.getOutputStream()));
    }

    private BufferedReader getReader(Socket socket) throws IOException {
        return new BufferedReader(new InputStreamReader(socket.getInputStream()));
    }

    private void talk() throws IOException {
        try {
            BufferedReader br = getReader(socketChannel.socket());
            PrintWriter pw = getWriter(socketChannel.socket());

            BufferedReader localReader = new BufferedReader(new InputStreamReader(System.in));
            String msg = null;
            while((msg = localReader.readLine()) != null){
                pw.println(msg);
                System.out.println(br.readLine());

                if (msg.equals("bye")){
                    break;
                }
            }
        }catch (IOException e){
            e.printStackTrace();
        }finally {
            try {
                socketChannel.close();
            }catch (IOException e){
                e.printStackTrace();
            }
        }

    }

    public static void main(String[] args) throws IOException {
        new EchoClient().talk();
    }
}
