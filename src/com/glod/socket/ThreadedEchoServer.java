package com.glod.socket;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;

/**
 *  多个客户端的服务器
 *
 */
public class ThreadedEchoServer {
    public static void main(String[] args) {
        try {
            int i = 1;
            ServerSocket s = new ServerSocket(8198);
            while(true){
                Socket incoming = s.accept();
                System.out.println("生产" + i);
                Runnable r = new ThreadedEchoHandler(incoming);
                Thread t = new Thread(r);
                t.start();
                i++;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

    }


}

/**
 *  回声线程
 */
class ThreadedEchoHandler implements Runnable{
    private Socket incoming;

    public ThreadedEchoHandler(Socket i){
        incoming = i;
    }

    @Override
    public void run() {
        InputStream is = null;
        OutputStream os = null;
        try {
            is = incoming.getInputStream();
            os = incoming.getOutputStream();
        } catch (IOException e) {
            e.printStackTrace();
        }

        Scanner in = new Scanner(is);
        PrintWriter pw = new PrintWriter(os,true);

        pw.println("Hello Enter BYE to exit");
        boolean done = false;
        if(in.nextLine().trim().equals("BYE")){
            done = true;
        }
        while(!done && in.hasNextLine()){
            String line = in.nextLine();
            pw.println("ECHO:" + line);

        }
        try {
            incoming.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

