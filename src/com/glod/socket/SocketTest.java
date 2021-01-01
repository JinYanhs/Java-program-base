package com.glod.socket;



import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Scanner;

/*
  Date: 2020-12-17\
  author: Glod

 */

public class SocketTest  {

    /**
     *   Function: socket 通信模拟TElnet命令访问远端服务器，获取返回一个美国的当日之时
     */
    public static void main(String args[])throws Exception{
        /**
         *
         *  没有建立到到达主机的初始连接将会一直阻塞
         */
        try (Socket s = new Socket("time-A.timefreq.bldrdoc.gov",13))
        {
            /**
             *
             *  如果主机不可达，应用将要阻塞好长时间，最终由于操作系统限制导致超时
             */
            s.setSoTimeout(2000); // 十秒后时间超时，如果这个时间内读写操作没有完成会抛出InterruptedIOException异常
            InputStream inS = s.getInputStream();
            Scanner in = new Scanner(inS);
            System.out.println("socket是否已经连接" + s.isConnected());
            System.out.println("socket是否已经关闭" + s.isClosed());
            while(in.hasNext())
            {
                String line = in.nextLine();
                System.out.println(line);
            }
        }
    }

    @Test
    public void getInetAddress(){
        try {
            InetAddress[] inetAddress = InetAddress.getAllByName("www.baidu.com");
           // byte[] addressBytes  = inetAddress.getAddress();
            for(InetAddress Address  : inetAddress){
               // 获取IP地址
                System.out.println(Address);
            }
        } catch (UnknownHostException e) {
            e.printStackTrace();
        }
    }

    /**
     *  Function:通过socket实现一个简单的逻辑处理服务器
     */
    @Test
    public void MyOwnServer() throws IOException {
        try(ServerSocket sSocket = new ServerSocket(8122)){
            try(Socket clients = sSocket.accept()){
                InputStream clientIn = clients.getInputStream();
                OutputStream serverOut = clients.getOutputStream();
                try(Scanner in = new Scanner(clientIn,"UTF-8")){
                    PrintWriter out = new PrintWriter(serverOut,true);
                    out.println("Hello,welcome to server! Input BYE to exit!" );
                    //判别
                    boolean isBye = false;
                    if("BYE".equals(in.nextLine().trim())){
                        isBye = true;
                        out.println("Okay,Have a good day");
                    }
                    while(!isBye && in.hasNextLine()){
                        String line = in.nextLine();
                        out.println("Echo: " + line);

                    }

                }
            }

        }

    }


}
