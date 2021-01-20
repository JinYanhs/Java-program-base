package com.glod.socket.networkProgramming.chapter4.NIOClient;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.CharBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.SocketChannel;
import java.nio.charset.Charset;
import java.util.Iterator;
import java.util.Set;

/**
 * @description: 非阻塞的EchoClient
 *               异步通信：发送数据和接收数据的操作互不干扰，各自独立进行。
 * @author: Glod
 * @date: 2021/1/20
 */
public class NEchoClient {
    private final int port = 8000;
    private SocketChannel socketChannel = null;
    // 发送数据存放
    private ByteBuffer sendBuffer = ByteBuffer.allocate(1024);
    // 接收数据存放
    private ByteBuffer receiveBuffer = ByteBuffer.allocate(1024);
    private Charset charset = Charset.forName("GBK");
    private Selector selector;

    public NEchoClient() throws IOException {
        socketChannel = SocketChannel.open();
        InetAddress ia = InetAddress.getLocalHost();
        InetSocketAddress isa = new InetSocketAddress(ia,port);
        socketChannel.connect(isa);
        // 设置为非阻塞模式
        socketChannel.configureBlocking(false);
        System.out.println("与服务器的连接建立成功");
        selector = Selector.open();

    }

    /**
     * 接收从控制台输入的数据，将之放到sendBuffer中
     */
    public void receiveFromUser() throws IOException {
        try {
            BufferedReader localReader = new BufferedReader(
                    new InputStreamReader(System.in)
            );
            String msg = null;
            while((msg = localReader.readLine()) != null){
                synchronized (sendBuffer){
                    sendBuffer.put(encode(msg + "\r\n"));
                }
                if (msg.equals("bye")){
                    break;
                }
            }
        }catch (IOException e){
            e.printStackTrace();
        }
    }

    // 接收和发送数据
    public void talk() throws IOException {
        socketChannel.register(selector, SelectionKey.OP_READ | SelectionKey.OP_WRITE);
        while(selector.select() > 0){
            Set readyKeys = selector.selectedKeys();
            Iterator it = readyKeys.iterator();
            while(it.hasNext()){
                SelectionKey key = null;
                try {
                    key = (SelectionKey) it.next();
                    it.remove();

                    if (key.isReadable()) {
                        receive(key);
                    }
                    if (key.isWritable()) {
                        send(key);
                    }
                }catch (IOException e){
                    e.printStackTrace();

                    try{
                        if(key != null){
                            key.cancel();
                            key.channel().close();
                        }
                    }catch (Exception ex){
                        ex.printStackTrace();
                    }
                }
            }

        }

    }

    private void send(SelectionKey key) throws IOException {
        // 发送sendBuffer中的数据
        SocketChannel socketChannel = (SocketChannel)key.channel();
        synchronized (sendBuffer){
            sendBuffer.flip();
            // 发送数据
            socketChannel.write(sendBuffer);
            // 删除已经发送的数据
            sendBuffer.compact();
        }
    }


    private void receive(SelectionKey key) throws IOException {
       //接收EchoServer发送的数据，放到receiveBuffer中
        //receiveBuffer中有这一行数据，就打印
        // 然后将之删除
        SocketChannel socketChannel = (SocketChannel) key.channel();
        socketChannel.read(receiveBuffer);

        receiveBuffer.flip();
        String receiveData = decode(receiveBuffer);

        if (receiveData.indexOf("\n") == -1){
            return;
        }

        String outputData = receiveData.substring(0,receiveData.indexOf("\n") + 1);
        System.out.println(outputData);
        if (outputData.equals("echo:bye\r\n")){
            key.cancel();
            socketChannel.close();
            System.out.println("关闭与服务器连接");
            selector.close();
            System.exit(0);// 结束程序
        }

        ByteBuffer temp = encode(outputData);
        receiveBuffer.position(temp.limit());
        receiveBuffer.compact(); // 删除已经打印的数据
    }

    private String decode(ByteBuffer buffer){
        CharBuffer charBuffer = charset.decode(buffer);
        return charBuffer.toString();
    }

    private ByteBuffer encode(String str){
        return charset.encode(str);
    }


    public static void main(String[] args) throws IOException {
        final NEchoClient client = new NEchoClient();
        Thread receiver = new Thread(()->{ // reciver线程
            try {
                // 接收用户向控制台输入的数据
                client.receiveFromUser();
            } catch (IOException e) {
                e.printStackTrace();
            }
        });

        receiver.start(); //启动Receiver线程
        client.talk();
    }
}
