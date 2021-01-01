package com.glod.socket.networkProgramming.chapter1;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

public class EchoServer {
    /**
     *  TCP/IP 参考模型
     *  应用层、传输层、网络互联层、主机-网络层
     */
    private int port = 8000;
    private ServerSocket serverSocket;

    public EchoServer()throws IOException {
        serverSocket = new ServerSocket(port);
        System.out.println("服务器启动");
    }

    public String echo(String msg){
        return "echo:" + msg;
    }

    private PrintWriter getWriter(Socket socket)throws IOException{
        OutputStream socketOut = socket.getOutputStream();
        // true表示每写一行，printWriter就缓存自动溢出，把数据写到目的地
        return new PrintWriter(socketOut,true);
    }

    private BufferedReader getReader(Socket socket)throws IOException{
        InputStream socketIn = socket.getInputStream();
        return new BufferedReader(new InputStreamReader(socketIn));
    }

    public void service(){
        while(true){
            Socket socket = null;
            try {
                socket = serverSocket.accept();//等待客户连接
                System.out.println("New connection accepted"
                        + socket.getInetAddress() + ":" + socket.getPort());
                BufferedReader br = getReader(socket);
                PrintWriter pw = getWriter(socket);
                String msg = null;
                while((msg = br.readLine()) != null){
                    System.out.println(msg);
                    pw.print(echo(msg));
                    if (msg.equals("bye")){
                        // 如果客户发送的消息为”bye”，则结束通信
                        break;
                    }
                }

            } catch (IOException e) {
                e.printStackTrace();
            }finally {
                    try {
                        // 断开连接
                        if (socket != null) {
                            socket.close();
                        }
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
            }
        }
    }

    public static void main(String[] args) throws IOException {
        new EchoServer().service();
    }

}
