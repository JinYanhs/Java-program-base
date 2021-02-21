package com.glod.IO.ios6;

import java.io.*;

/**
 * @description:
 * @author: Glod
 * @date: 2021/2/21
 */
public class TestOther4 {
    /**
     *  字节流：
     *  节点流：FileInputStream FileOutPutStream
     *  处理流：BufferedInputStream DataInputStream PrintStream : [  public PrintStream(String fileName) throws FileNotFoundException {
     *         this(false, new FileOutputStream(fileName));
     *     }]
     *
     */

    ByteArrayOutputStream baos; // 目的地是一个字节数组 节点流
    ByteArrayInputStream bais;  // 数据源是一个字节数组 节点流
    CharArrayReader bar; //节点流
    CharArrayWriter baw;  //节点流

    // IO体系中的设计模式
    //1. 装饰模式
    //2. 适配器模式 InputStreamReader OutputStreamWriter
    byte [] buf = new byte[1024];
    DataInputStream dis = new DataInputStream(new BufferedInputStream(new ByteArrayInputStream(buf)));
    DataInputStream dis2 = new DataInputStream(new BufferedInputStream(new FileInputStream("E:/Java/testFile/Java.txt")));

    // 节点流的父类：InputStream
    // 装饰模式（节点流）：ByteArrayInputStream 冰淇凌蛋糕 FileInputStream 奶油蛋糕
    // 处理流的父类：FilterInputStream extends InputStream
    // 处理流： BufferedInputStream 蜡烛 DataInputSteam 卡片

    public TestOther4() throws FileNotFoundException {
    }
}
