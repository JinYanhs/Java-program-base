package com.glod.socket.networkProgramming.chapter2;

import java.io.IOException;
import java.net.*;

/**
 * @description: 接收用户从命令行输入的主机名和端口，然后连接到该地址，如果连接成功，就会计算建立连接的时间；如果链接失败，就会捕获各种异常。
 * @author: Glod
 * @date: 2021/1/2
 */
public class ConnectTester {
    public static void main(String[] args) {
        String host = "www.javathinker.net";
        int port = 80;

        if(args.length > 1){
            host = args[0];
            port = Integer.parseInt(args[1]);
        }
        new ConnectTester().connect(host,port);
    }

    public void connect(String host,int port){
        SocketAddress remoteAddr = new InetSocketAddress(host,port);
        Socket socket = null;
        String result = "";
        try {
            long begin = System.currentTimeMillis();
            socket = new Socket();
            // bind()方法设定绑定到本地的IP地址和端口,抛出BindException的情形
            socket.bind(new InetSocketAddress(InetAddress.getByName("222.34.5.7"),5678));
            // 超时时间为1s
            socket.connect(remoteAddr,1000);
            long end = System.currentTimeMillis();
            // 计算连接所花的时间
            result = (end-begin) + "ms";
        }catch (BindException e){
            result = "Local address and port cant be binded";
        }catch (UnknownHostException e){
            result = "Unknown Host";
        }catch(ConnectException e){
            result = "Connection Refused";
        }catch(SocketTimeoutException e){
            result = "TimeOut";
        }catch (IOException e){
            result = "failure";
        }finally{
            try{
                if(socket != null){
                    socket.close();
                }
            }catch(IOException e){
                    e.printStackTrace();
            }
        }
        System.out.println(remoteAddr + ":" + result);
    }
}
