package com.glod.IO.io1;

import java.io.*;

/**
 * @description: 文件拷贝 处理异常 try catch  1.7之前显式关闭
 * @author: Glod
 * @date: 2021/2/17
 */
public class TestCopy2 {
    public static void main(String[] args){

        // 1.创建输入流和输出流（小文本）
        File fromFile = new File("E:/Java/testFile/Java.txt");
        File toFile = new File("E:/Java/testFile/toJava.txt");


        long startTime = System.currentTimeMillis();
        InputStream  fis = null;
        OutputStream fos = null;
        try {
            fis = new FileInputStream(fromFile);
            fos = new FileOutputStream(toFile); // 和上一语句存在依赖关系（读出错，就不用考虑写了），写在此处上条语句出错后，此条不执行
            // 2.使用输入流和输出流完成文件复制
            int n; // 定义一个中转站，就是一个字节

            // 先读取一个字节
            n = fis.read();
            while(n != -1){ // n == -1 代表读到了文件的末尾
                // 写一个字节
                fos.write(n);
                //System.out.println(n);
                // 再读一个字节
                n = fis.read();

            }


        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            // 3.关闭输入流和输出流 （出不出异常都要关闭）
            try {
                if (fis != null) // 不为null关闭
                    fis.close();
                // fos.close(); 不能这样子写在上一个关闭流下面，会导致这个流没有关闭
            } catch (IOException e) {
                e.printStackTrace();
            }

            try {
                if (fos != null) // 不为null关闭
                    fos.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
            long endTime = System.currentTimeMillis();
            System.out.println("oneByteOnceCopy复制完毕！耗时：" + (endTime - startTime) + "ms");
        }


    }




}
