package com.glod.IO.io5;

import com.glod.IO.Student;

import java.io.*;
import java.util.Date;

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
public class TestObjectStream {

    private static Student student = new Student("张三",123,24);
    public static void main(String[] args) throws IOException, ClassNotFoundException {
       // write();
        read();
    }

    private static void write() throws IOException {

        OutputStream fos = new FileOutputStream(new File("E:/Java/testFile/JavaData.txt")); // 节点流
        BufferedOutputStream bos = new BufferedOutputStream(fos); // 提高速度
        ObjectOutputStream oos = new ObjectOutputStream(bos);
        // 写入基本数据类型和字符串 写入二进制数据
        oos.writeInt(10);
        oos.writeDouble(3.14);
        oos.writeChar('A');
        oos.writeUTF("开啊开");
        oos.writeObject(student);

        oos.writeObject(new Date());
        //关闭流
        oos.close();
    }

    private static void read() throws IOException, ClassNotFoundException {
        InputStream fis = new FileInputStream(new File("E:/Java/testFile/JavaData.txt")); // 节点流
        BufferedInputStream bis = new BufferedInputStream(fis); // 提高速度
        ObjectInputStream ois = new ObjectInputStream(bis);
        // 读基本数据类型和字符串 需严格按照格式写入顺序读取
        System.out.println(ois.readInt());
        System.out.println(ois.readDouble());
        System.out.println(ois.readChar());
        System.out.println(ois.readUTF());
        System.out.println(ois.readObject());
        Date date = (Date) ois.readObject();
        System.out.println(date.toLocaleString());
        //关闭流
        ois.close();
    }
}
