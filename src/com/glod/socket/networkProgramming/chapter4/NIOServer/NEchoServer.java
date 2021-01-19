package com.glod.socket.networkProgramming.chapter4.NIOServer;

import com.glod.socket.networkProgramming.chapter3.closeServer.EchoServer;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.CharBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.nio.charset.Charset;
import java.util.Iterator;
import java.util.Set;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @description: 非阻塞下的EchoServer。ServerSocketChannel和SocketChannel都被设置为非阻塞模式，
 *               这使得接收连接、接收数据和发送数据的操作都采用非阻塞模式并且采用一个线程同时完成。
 * @author: Glod
 * @date: 2021/1/17
 */
public class NEchoServer {

    /**
     * 非阻塞模式下，EchoServer只需要启动一个主线程，可同时处理三件事
     * <p>
     * 1.接收客户的连接
     * 2.接收客户发送的数据
     * 3.向客户发回响数据
     */
    private int port = 8000;
    private ServerSocketChannel serverSockerChannel = null;
    // private ExecutorService executorService; //线程池
    private static final int POOL_MULTIPLE = 4; //线程池中工作线程的数目
    private Selector selector = null;
    private Charset charset = Charset.forName("GBK");


    public NEchoServer() throws IOException {
        // 创建一个线程池
        // executorService = Executors.newFixedThreadPool(Runtime.getRuntime().availableProcessors() * POOL_MULTIPLE);

        selector = Selector.open();
        // 创建一个ServerSocketChannel对象
        serverSockerChannel = ServerSocketChannel.open();
        // 使得在同一个主机上关闭了服务器程序，紧接着再启动该服务器程序时，可以顺利绑定上相同端口
        serverSockerChannel.socket().setReuseAddress(true);
        serverSockerChannel.configureBlocking(false);
        // 将服务器与本地端口绑定
        serverSockerChannel.socket().bind(new InetSocketAddress(port));

        // selector
        System.out.println("服务器启动");
    }

    public void service() throws IOException {
        serverSockerChannel.register(selector, SelectionKey.OP_ACCEPT);
        while (selector.select() > 0) { // select到事件
            // 获得Selector的selected-keys集合
            Set readyKeys = selector.selectedKeys();
            Iterator it = readyKeys.iterator();
            while (it.hasNext()) {
                SelectionKey key = null;
                try {
                    key = (SelectionKey) it.next(); //迭代出一个SelectionKey
                    // 把SelectionKey从Selector的selected-key集合中删除
                    it.remove();
                    // 事件处理
                    if (key.isAcceptable()) {
                        // 处理连接就绪事件
                        handleAccept(key);
                    }
                    if (key.isReadable()) {
                        // 处理读就绪事件
                        handleRead(key);
                    }
                    if (key.isWritable()) {
                        // 处理写就绪事件
                        handleWrite(key);
                    }


                } catch (IOException e) {
                    e.printStackTrace();
                    if (key != null) {
                        // 使这个SelectionKey失效，
                        // 使得Selector不再监控这个SelectionKey感兴趣的事件
                        key.cancel();
                        // 关闭与这个key关联的SocketChannel
                        key.channel().close();
                    }
                }
            }
        }
    }

    // 处理连接就绪
    private void handleAccept(SelectionKey key) {
        // 获得与SelectionKey关联的ServerSocketChannel
        ServerSocketChannel ssc = (ServerSocketChannel) key.channel();

        // 获得与客户连接的SocketChannel
        try {
            SocketChannel socketChannel = ssc.accept();
            System.out.println("接收到客户连接,来自：" +
                    socketChannel.socket().getInetAddress() +
                    ":" + socketChannel.socket().getPort()
            );
            // 将SocketChannel设置为非阻塞模式
            socketChannel.configureBlocking(false);
            // 创建一个用于存放用户发送来的数据的缓冲去
            ByteBuffer buffer = ByteBuffer.allocate(1024);
            // SocketChannel向Selector注册读就绪事件和写就绪时间
            socketChannel.register(selector, SelectionKey.OP_READ | SelectionKey.OP_WRITE, buffer); //关联一个buffer附件
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // 处理读就绪事件
    public void handleRead(SelectionKey key) {
        // 获得与SelectionKey关联的附件
        ByteBuffer buffer = (ByteBuffer) key.attachment();
        // 获得与SelectionKey关联的SocketChannel
        SocketChannel socketChannel = (SocketChannel) key.channel();
        // 创建一个ByteBuffer,来存放读到的数据
        ByteBuffer readBuff = ByteBuffer.allocate(32);

        try {
            socketChannel.read(readBuff);
            readBuff.flip();

            // 把buffer的极限设置为容量
            buffer.limit(buffer.capacity());
            // 把readBuff中的内容拷贝到buffer中
            // 假定buffer的容量足够大，不会出现缓冲区异常
            buffer.put(readBuff);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void handleWrite(SelectionKey key) throws IOException {
        ByteBuffer buffer = (ByteBuffer) key.attachment();
        SocketChannel socketChannel = (SocketChannel) key.channel();
        buffer.flip();  // 把极限设为位置，把位置设为0
//        Charset charset = Charset.forName("GBK");
//        String strData = buffer.asCharBuffer().toString();
        String strData = decode(buffer);
        if (strData.indexOf("\r\n") == -1) return;
        String outputData = strData.substring(0, strData.indexOf("\n") + 1);
        System.out.println(outputData);
        // 把输出的字符串按照GBK编码，转换为字节，把他放到outPutBuffer中
        ByteBuffer outputBuffer = encode("echo:" + outputData);
        //输出outputBuffer中所有的字节
        while (outputBuffer.hasRemaining()) {
            socketChannel.write(outputBuffer);

            // 把outputData字符串按照GBK编码，转换为字节，把它放在ByteBuffer中
            ByteBuffer temp = encode(outputData);
            // 把buffer的位置设置为temp的极限
            buffer.position(temp.limit());
            // 删除buffer中已经处理的数据 buffer.compact()
            buffer.compact();
            // 如果已经输出了字符串”bye\r\n",就使SelectionKey失效，并关闭SocketChannel
            if (outputData.equals("bye\r\n")) {
                key.cancel();
                socketChannel.close();
                System.out.println("关闭与客户端的连接");
            }


        }
    }

    private String decode(ByteBuffer byteBuffer) {
        // 解码
        CharBuffer charBuffer = charset.decode(byteBuffer);
        return charBuffer.toString();
    }

    private ByteBuffer encode(String str) {
        // 编码
        return charset.encode(str);
    }

    public static void main(String[] args) throws Exception {
        NEchoServer server = new NEchoServer();
        server.service();

    }


}
