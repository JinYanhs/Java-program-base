package com.glod.IO.io1;

import java.io.*;

/**
 * @description: 文件拷贝  try with resource 1.7隐式关闭
 * @author: Glod
 * @date: 2021/2/17
 */
public class TestCopy3 {
    public static void main(String[] args){

        // 1.创建输入流和输出流（小文本）
        File fromFile = new File("E:/Java/testFile/Java.txt");
        File toFile = new File("E:/Java/testFile/toJava.txt");


        long startTime = System.currentTimeMillis();

        try(  // 实例化对象放入这个里面都不用手动关了，实现AutoCloseable接口的对象才可放入
                InputStream  fis = new FileInputStream(fromFile);
              OutputStream fos = new FileOutputStream(toFile);
              ){

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
        }
            long endTime = System.currentTimeMillis();
            System.out.println("oneByteOnceCopy复制完毕！耗时：" + (endTime - startTime) + "ms");



    }




}
