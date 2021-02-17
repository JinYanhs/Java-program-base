package com.glod.IO.io2;

import java.io.*;

/**
 * @description: 节点流 FileReader/FileWriter  中转：一个字符
 * @author: Glod
 * @date: 2021/2/17
 */
public class TestCopy1 {
    public static void main(String[] args) throws IOException {
        // 1.创建输入流和输出流
        Reader fr = new FileReader("E:/Java/testFile/Java.txt"); // 源文件不是UTF-8，复制后的toJava2中文会是乱码
        Writer fw = new FileWriter("E:/Java/testFile/toJava2.txt");

        // 2.使用输入和输出流复制文件
        // 2.1 定义中转站：一个字符
        int n = fr.read();
        while (n != -1){
            // 写一个字符到文件
            fw.write(n);
            //System.out.println(n);
            System.out.println((char)n);
            // 再读取一个字符到中转站
            n = fr.read();
        }

        // 3.关闭流
        fr.close();
        fw.close();
    }
}
