package com.glod.IO.io1;

import javax.print.attribute.standard.RequestingUserName;
import java.io.*;

/**
 * @description: 文件拷贝 节点流
 * @author: Glod
 * @date: 2021/2/17
 */
public class TestCopy1 {
    public static void main(String[] args) throws IOException {

        // 1.创建输入流和输出流（小文本）
        File fromFile = new File("E:/Java/testFile/Java.txt");
        File toFile = new File("E:/Java/testFile/toJava.txt");
        oneByteOnceCopy(fromFile,toFile);

        // 2.大视频
        File fromVdi = new File("E:/Java/testFile/vdi.mp4");
        File toVdi = new File("E:/Java/testFile/toVdi.mp4");
        oneKBOnceCopy(fromVdi,toVdi);
    }

    /**
     *  一次拷贝一个字节，适用于小文件，而不适用于大文件如：视频
     * @param fromFile 来源source
     * @param toFile 写入source
     */
    private static void oneByteOnceCopy(File fromFile,File toFile) throws IOException
        {
            long startTime = System.currentTimeMillis();
            // 复制的时候，文本中原来的换行也会保存保持
            /**
             *  传入String也可
             *  public FileInputStream(String name) throws FileNotFoundException {
             *         this(name != null ? new File(name) : null);
             *     }
             */
        InputStream  fis = new FileInputStream(fromFile);
        OutputStream  fos = new FileOutputStream(toFile,true);  // 两个参数 1.File 2.boolean -> append true:追加 false:覆盖

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

        System.out.println("复制完毕！");
        // 3.关闭输入流和输出流
        fis.close();
        fos.close();
        long endTime = System.currentTimeMillis();
        System.out.println("oneByteOnceCopy复制完毕！耗时：" + (endTime - startTime) + "ms");
    }

    private static void oneKBOnceCopy(File fromFile,File toFile) throws IOException
    {
        long startTime = System.currentTimeMillis();
        InputStream  fis = new FileInputStream(fromFile);
        OutputStream    fos = new FileOutputStream(toFile);

        // 2.使用输入流和输出流完成文件复制
       byte[] buf = new byte[1024]; // 定义一个中转站，就是一个字节数组  2048:耗时2411ms 1024:耗时4760ms

        // 先读取内容到字节数组
        int len = fis.read(buf); // 将读取文件的内容放入到字节数组中，返回读取的字节数
        while(len != -1){ // len == -1 代表读到了文件的末尾
            // 将字节数组内容写入文件
            // fos.write(buf); buf固定1024，会导致末尾会多写
            fos.write(buf,0,len);
            //System.out.println(buf);
            // 再读一个字节数组
            len = fis.read(buf);

        }

        // 3.关闭输入流和输出流
        fis.close();
        fos.close();
        long endTime = System.currentTimeMillis();
        System.out.println("oneKBOnceCopy复制完毕！耗时：" + (endTime - startTime) + "ms");
    }
}
