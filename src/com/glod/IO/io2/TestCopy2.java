package com.glod.IO.io2;

import java.io.*;

/**
 * @description: FileReader/FileWriter  中转：一个字符数组
 *               使用字符流只能读写文本文件（记事本可打开的文件），doc也并非纯文本文件
 *
 *               字符流好处：处理非英文字符方便（一次基本就可以读一个汉字），字节流要两次或三次
 *
 *               只有字节流，没有字符流（底层还是字节流）
 *
 *
 * @author: Glod
 * @date: 2021/2/17
 */
public class TestCopy2 {
    public static void main(String[] args) throws IOException {
        // 1.创建输入流和输出流
        Reader fr = new FileReader("E:/Java/testFile/Java.txt"); // 源文件不是UTF-8，复制后的toJava2中文会是乱码
        Writer fw = new FileWriter("E:/Java/testFile/toJava2.txt");

        // 2.使用输入和输出流复制文件
        // 2.1 定义中转站：一个字符数组
        char[] cBuf = new char[1024];
        int len = fr.read(cBuf);
        while (len != -1){
            // 写一个字符数组到文件
            fw.write(cBuf,0,len);

           //System.out.println(cBuf);
            System.out.println(new String(cBuf,0,len));
            // 再读取一个字符数组到中转站
            len = fr.read(cBuf);
        }

        // 3.关闭流
        fr.close();
        fw.close();
    }
}
