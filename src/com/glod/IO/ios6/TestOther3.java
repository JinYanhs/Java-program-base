package com.glod.IO.ios6;

import java.io.*;

/**
 * @description: 字节流转字符流 转换流（字符流）
 *                转换流  字节流转换为字符流->操作简单
 *                    InputStreamReader
 *                    OutputStreamWriter
 *
 *                    设计模式：适配器模式
 *
 * @author: Glod
 * @date: 2021/2/21
 */
public class TestOther3 {
    public static void main(String[] args) throws IOException {
        InputStream in = System.in;
        BufferedReader br =  new BufferedReader(new InputStreamReader(in)); // 字节流转字符流
        //BufferedReader br =  new BufferedReader(new FileReader("E:/Java/testFile/Java.txt"));
        // BufferedWriter bw = new BufferedWriter(new FileWriter("E:/Java/testFile/Java3.txt"));
        PrintWriter pw = new PrintWriter("E:/Java/testFile/Java3.txt");

        String str = br.readLine();
        while (!str.equals("bye")){
//            bw.write(str);
//
//            bw.newLine();

            pw.println(str);

            str = br.readLine();
        }

        br.close();
        pw.close();
    }

}
