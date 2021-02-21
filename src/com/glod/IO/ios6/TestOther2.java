package com.glod.IO.ios6;

import java.io.*;

/**
 * @description: pw写入文件
 * @author: Glod
 * @date: 2021/2/21
 */
public class TestOther2 {
    public static void main(String[] args) throws IOException {
        BufferedReader br =  new BufferedReader(new FileReader("E:/Java/testFile/Java.txt"));
        // BufferedWriter bw = new BufferedWriter(new FileWriter("E:/Java/testFile/Java3.txt"));
        PrintWriter pw = new PrintWriter("E:/Java/testFile/Java3.txt");

        String str = br.readLine();
        while (str != null){
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
