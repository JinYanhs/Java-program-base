package com.glod.socket.networkProgramming.chapter3.mutithread1;

import com.sun.xml.internal.ws.policy.privateutil.PolicyUtils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

/**
 * @description: 处理器类负责与单个客户通信
 * @author: 金研
 * @date: 2021/1/5
 */
public class Handler implements Runnable {
    private Socket socket;
    public Handler(Socket socket){
        this.socket = socket;
    }

    private PrintWriter gerWriter(Socket socket)throws IOException{
        return new PrintWriter(socket.getOutputStream());
    }

    private BufferedReader getReader(Socket socket)throws IOException{
        return new BufferedReader(new InputStreamReader(socket.getInputStream()));
    }

    public String echo(String msg){
        return "ECHO:" + msg;
    }


    @Override
    public void run() {
        try {
            System.out.println("New connection accepted" +
                    socket .getInetAddress() + ":" + socket.getPort()
            );
            BufferedReader br = getReader(socket);
            PrintWriter pw = gerWriter(socket);

            String msg = null;
            // 接收和发送数据，直到通信结束
            while((msg = br.readLine()) != null){
                System.out.println("from" + socket.getInetAddress() + ":" + socket.getPort() + ">" + msg);
                pw.print(echo(msg));
                if (msg.equals("bye")){
                    break;
                }
            }
        }catch (IOException e){
            e.printStackTrace();
        }finally {
            try {
                if (socket != null)
                socket.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

    }
}
