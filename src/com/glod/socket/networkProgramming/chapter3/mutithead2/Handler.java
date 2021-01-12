package com.glod.socket.networkProgramming.chapter3.mutithead2;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

/**
 * @description: 处理连接类
 * @author: Glod
 * @date: 2021/1/10
 */
public class Handler implements Runnable {
    private Socket socket;
    public Handler(Socket socket){
        this.socket = socket;
    }

    private PrintWriter getPrintWriter(Socket socket) throws IOException {
        // true表示每写一行，printWriter就缓存自动溢出，把数据写到目的地 !!不为true时候写不出去
        return new PrintWriter(socket.getOutputStream(),true);
    }

    private BufferedReader getReader(Socket socket) throws IOException{
        return new BufferedReader(new InputStreamReader(socket.getInputStream()));
    }

    private String echoMsg(String msg){
        return "Echo:" + msg;
    }
    @Override
    public void run() {
        System.out.println("New connection accepted" + socket.getInetAddress() + ":" + socket.getPort());

        try {
            BufferedReader bufferedReader = getReader(socket);
            PrintWriter printWriter = getPrintWriter(socket);
            String msg = null;
            // 接收和发送数据，直到通信结束
            System.out.println(bufferedReader.readLine());
            while((msg = bufferedReader.readLine()) != null){
                System.out.println("from " + socket.getInetAddress() + ":" + socket.getPort() + ">" + msg);
                printWriter.print(echoMsg(msg));
                if ("bye".equals(msg)){
                    break;
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }finally {
//            if (socket != null) {
//                try {
//                    socket.close();  // 断开连接
//                    System.out.println("关闭");
//                } catch (IOException e) {
//                    e.printStackTrace();
//                }
//            }
        }

    }
}
