package com.glod.IO.ios6;

import java.io.*;
import java.util.Scanner;

/**
 * @description: 测试其他流
 * @author: Glod
 * @date: 2021/2/21
 */
public class TestOther {
    public static void main(String[] args) throws FileNotFoundException {
        /**
         *  打印流: 只有输出流，没有输入流  都是处理流
         *
         *  println()是PrintStream的 可以将各种类型的数据写入文件，不能保留原来的类型，写入后都变成String类型 可读的
         */
        // PrintStream ps = System.out; // 字节流 输出流
        PrintWriter pw; // 字符流 输出流 Servlet 动态网页
        PrintStream ps = new PrintStream("E:/Java/testFile/psJava.txt");

        ps.println("Hello Java");

        /**
         *  扫描器
         */
        InputStream is = System.in; // 扫描来自键盘的读
        InputStream FileIs = new FileInputStream(new File("E:/Java/testFile/psJava.txt")); // 扫描来自文件的读取
        Scanner scanner = new Scanner(FileIs);
        String str = scanner.next();
        System.out.println(str);
    }
}
