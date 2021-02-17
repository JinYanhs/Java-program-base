package com.glod.IO.io5;

import java.io.*;
import java.lang.reflect.Field;

/**
 * @description: 将各种基本数据类型和引用数据类型方便的写入到文件中并方便的读取出来
 *                          字节流而非字符流
 *                          处理流而非节点流
 *              DataInputStream DataOutputStream  可以写入基本数据类型和字符串
 *              ObjectInputStream ObjectOutputStream 相比DataXXXStream还可以操作对象
 *              写入数据是二进制，无法通过记事本直接查看
 *              写入数据需要对应输入流来读取
 * @author: Glod
 * @date: 2021/2/17
 */
public class TestDataStream {
    public static void main(String[] args) throws IOException {
        write();
        read();
    }

    private static void write() throws IOException {

        OutputStream fos = new FileOutputStream(new File("E:/Java/testFile/JavaData.txt")); // 节点流
        BufferedOutputStream bos = new BufferedOutputStream(fos); // 提高速度
        DataOutputStream dos = new DataOutputStream(bos);
        // 写入基本数据类型和字符串 写入二进制数据
        dos.writeInt(10);
        dos.writeDouble(3.14);
        dos.writeChar('A');
        dos.writeUTF("开啊开");
        //关闭流
        dos.close();
    }

    private static void read() throws IOException {
        InputStream fis = new FileInputStream(new File("E:/Java/testFile/JavaData.txt")); // 节点流
        BufferedInputStream bis = new BufferedInputStream(fis); // 提高速度
        DataInputStream dis = new DataInputStream(bis);
        // 读基本数据类型和字符串 需严格按照格式写入顺序读取
        System.out.println(dis.readInt());
        System.out.println(dis.readDouble());
        System.out.println(dis.readChar());
        System.out.println(dis.readUTF());
        //关闭流
        dis.close();
    }
}
