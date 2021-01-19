package com.glod.socket.networkProgramming.chapter4.NIOServer;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.nio.ByteBuffer;
import java.nio.CharBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.nio.charset.Charset;
import java.util.Iterator;
import java.util.Set;

/**
 * @description: 采用混合阻塞模式与非阻塞模式。如有好多客户连接请求，可以把接收客户连接的操作单独由一个线程完成，
 *              把接收数据和发送数据的操作由另一个线程完成，可提高服务器的并发性能。
 *              不同于NEchoServer，NEchoServer2的ServerSocketChannel采用默认的阻塞模式。
 * @author: Glod
 * @date: 2021/1/19
 */
public class NEchoServer2 {
    private Selector selector = null;
    private ServerSocketChannel serverSocketChannel = null;
    private int port = 8000;
    private Charset charset = Charset.forName("GBK");

    public NEchoServer2() throws IOException {
        // 通过静态工厂方法实例化Selector和ServerSocketChannel对象
        selector = Selector.open();
        serverSocketChannel = ServerSocketChannel.open();
        // 使得在同一个主机上关闭了服务器程序，紧接着再启动该服务器程序时，可以顺利绑定上相同端口
        serverSocketChannel.socket().setReuseAddress(true);
        serverSocketChannel.bind(new InetSocketAddress(port));
        System.out.println("服务器启动");
    }

    public void accept(){
        for(;;){
            try {
                SocketChannel socketChannel = serverSocketChannel.accept();
                System.out.println("接收到客户连接，来自：" +
                        socketChannel.socket().getInetAddress() +
                        ":" + socketChannel.socket().getPort());
                // 配置SocketChannel为非阻塞
                socketChannel.configureBlocking(false);

                ByteBuffer byteBuffer = ByteBuffer.allocate(1024);
                // 同步（对操纵共享资源的代码块进行了同步，从而避免对共享资源的竞争）
                synchronized (gate){
                    // wakeup()方法
                    selector.wakeup();

                    // 将事件注册到all-keys集合
                    socketChannel.register(selector, SelectionKey.OP_READ | SelectionKey.OP_WRITE,byteBuffer);
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     *  Acceptor线程向Selector注册了事件，并且该事件发生后，主线程才会从selector.select()方法中返回。
     *  加入Selector中没有任何注册的时间，由于主线程在selector.select()中阻塞，这使得Acceptor线程也在socketChannel.register()中阻塞
     *  。Acceptor线程无法向Selector注册时间，而主线程没有任何事件可以监控，所以两个线程就永远阻塞下去。----导致死锁产生（抢占同一个资源--
     *  Selector对象的共享资源all-keys集合）
     */
    // 锁
    private Object gate = new Object();

    public void service()throws IOException{
        for (;;){
            synchronized (gate){

                // select 查询已经发生的事件，然后做出相应的响应
                int n = selector.select();
                // 没有事件触发，跳过此次循环
                if (n == 0){
                    continue;
                }
                Set readyKeys = selector.selectedKeys();
                Iterator it = readyKeys.iterator();
                while(it.hasNext()){
                    SelectionKey key = null;
                    try {
                        key = (SelectionKey) it.next();
                        // 把SelectionKey从Selector的selected-key集合中删除 不移除会
                        it.remove();
                        if (key.isReadable()) {
                            receive(key);
                        }

                        if (key.isWritable()) {
                            send(key);
                        }
                    }catch(IOException e){
                        e.printStackTrace();
                        try{
                            if (key != null){
                                key.cancel();
                                // 关闭通道，socket
                                key.channel().close();
                            }
                        }catch (Exception ex){
                            e.printStackTrace();
                        }
                    }
                }
            }
        }
    }

    /**
     *  发送数据
     * @param key selectionKey
     */
    private void send(SelectionKey key) throws IOException {
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

    /**
     *  读取客户端数据
     * @param key selectionKey
     */
    private void receive(SelectionKey key) {
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

    private String decode(ByteBuffer byteBuffer) {
        // 解码
        CharBuffer charBuffer = charset.decode(byteBuffer);
        return charBuffer.toString();
    }

    private ByteBuffer encode(String str) {
        // 编码
        return charset.encode(str);
    }

    public static void main(String[] args) throws IOException {
        final NEchoServer2 server = new NEchoServer2();
        // 匿名内部类 Acceptor线程，负责接收客户连接
        new Thread(()->{
            server.accept();
        }).start();
        // 主线程启动Accepter后，负责执行service()方法
        server.service();
    }
}
