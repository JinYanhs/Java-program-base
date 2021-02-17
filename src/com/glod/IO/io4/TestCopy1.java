package com.glod.IO.io4;

import java.io.*;

/**
 * @description: 按行读取
 *
 *              使用缓冲流的好处
 *              1.提高速度 BufferedInputStream BufferedReader
 *              2.简化操作 BufferedReader
 *
 *              reaLine()原理：
 *              StringBuilder builder = new StringBuilder("");
 *              int n = br.read();
 *              builder.append((char)n);
 *              int n = br.read();
 *              builder.append((char)n);
 *              .......
 *              读到了一行的末尾
 *              builder.toString();
 * @author: Glod
 * @date: 2021/2/17
 */
public class TestCopy1 {
    public static void main(String[] args) throws IOException {
        // 创建输入/输出流
        BufferedReader br = new BufferedReader(new FileReader("E:/Java/testFile/Java.txt"));
        BufferedWriter bw = new BufferedWriter(new FileWriter("E:/Java/testFile/toJava3.txt"));
        // 使用流复制文件，按行读写
         //先读一行
        String str = br.readLine();
        while (str != null){
            // 写一行
            bw.write(str);
            System.out.println(str);
            // 需要手动换行
           // bw.write("\n"); 不同操作系统中换行符的表示不同 Mac:\r Windows:\r\n
            bw.newLine(); // 不用操心不同操作系统不同实现
            // 再读一行
            str = br.readLine();
        }

        // 关闭流
        br.close();
        bw.close();
    }
}
