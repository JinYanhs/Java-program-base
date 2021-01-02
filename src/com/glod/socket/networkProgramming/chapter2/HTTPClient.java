package com.glod.socket.networkProgramming.chapter2;

import com.sun.org.apache.xml.internal.serializer.OutputPropertiesFactory;
import com.sun.xml.internal.ws.commons.xmlutil.Converter;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;

/**
 * @description: 用于访问网页，创建一个连接到HTTP服务器的socket对象，然后发送符合HTTP的请求，接着就收从HTTP服务器上发回的响应结果。
 * @author: 金研
 * @date: 2021/1/2
 */
public class HTTPClient {
    String host = "www.baidu.com";
    int port = 80;
    Socket socket;

    public void createSocket()throws Exception{
        socket = new Socket(host,port);
    }

    public void communicate()throws Exception{
        StringBuffer sb = new StringBuffer("GET " + "/" + " HTTP/1.1\r\n");
        sb.append("Host:" + host + "\r\n");
        sb.append("Accept: */*\r\n");
        sb.append("Accept-Language: zh-cn\r\n");
        sb.append("Accept-Encoding: gzip,deflate\r\n");
        sb.append("User-Agent:HTTPClient\r\n");
        sb.append("Connection: Keep-Alive\r\n\r\n");

        // 发出HTTP请求
        OutputStream socketOut = socket.getOutputStream();
        //System.out.println(sb.toString().getBytes());
        socketOut.write(sb.toString().getBytes());
        socketOut.flush();

        //接收响应结果
        InputStream socketIn = socket.getInputStream();
        ByteArrayOutputStream buffer = new ByteArrayOutputStream();

        byte[] buff = new byte[1024];
        int len = -1;

        while((len = socketIn.read(buff)) != -1){
            buffer.write(buff,0,len);
        }

        // 把字节数组转换为字符串

        System.out.println(new String(buffer.toByteArray()));
        socket.close();
    }

    public static void main(String[] args) throws Exception{
        HTTPClient client = new HTTPClient();
        client.createSocket();
        client.communicate();
    }

//    @Test
//    public void test(){
//        String str = new String("333333333333");
//        System.out.println(str.getBytes());
//    }
}
