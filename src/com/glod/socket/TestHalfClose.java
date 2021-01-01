package com.glod.socket;

  import java.io.PrintWriter;
        import java.net.Socket;
        import java.util.Scanner;

/**
 *  半关闭
 */
public class TestHalfClose {
    public static void main(String[] args) throws Exception {
        /**
         *  服务器将读取输入信息，知道到达输入流的结尾，然后它在发送响应
         *   该协议只适用于一站式服务，如HTTP协议，在这种服务中，客户端连接服务器，发送一个请求，捕获响应信息，然后断开连接
         */
        Socket socket = new Socket();
        Scanner in = new Scanner(socket.getInputStream());
        PrintWriter writer = new PrintWriter(socket.getOutputStream());
        // 发送请求数据
        writer.println("-----");
        writer.flush();
        socket.shutdownInput();
        // 新的Socket是半关闭
        // 读取相应数据
        while(in.hasNextLine()){
            String line = in.nextLine();
        }
        socket.close();
    }

}
