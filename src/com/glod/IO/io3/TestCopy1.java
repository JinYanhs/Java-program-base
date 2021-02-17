package com.glod.IO.io3;

import java.io.*;

/**
 * @description:  处理流：提升传输性能或程序灵活性
 *
 *                  1.缓冲流可以提高读写速度
 *                  2.关闭缓冲流，无需要再关闭底层流
 *                  3.为什么速度快？
 *                      引入了输入输出两个缓冲区，大大减少了读写硬盘的次数
 *                  4.如何刷新输出缓冲区（将缓存区的最新数据写入文件中）
 *                      方法1：bos.close(); 底层会先刷新再关闭流
 *                      方法2：bos.flush()
 *                      方法3：缓冲去满，自动刷新（自定义的缓冲区大于默认的8192）
 * @author: Glod
 * @date: 2021/2/17
 */
public class TestCopy1 {
    public static void main(String[] args){

        // 1.创建输入流和输出流（小文本）
        File fromFile = new File("E:/Java/testFile/vdi.mp4");
        File toFile = new File("E:/Java/testFile/toVdi.mp4");


        long startTime = System.currentTimeMillis();

        try(  // 实例化对象放入这个里面都不用手动关了，实现AutoCloseable接口的对象才可放入
                InputStream  fis = new FileInputStream(fromFile); // 单纯节点流一次一字节100m视频复制，。。。。。。。。。。。。。
              OutputStream fos = new FileOutputStream(toFile);

              // 处理流关了，节点流也会关闭，不关闭流的话，会导致部分数据写入不到目标文件中
              BufferedInputStream bis = new BufferedInputStream(fis); // 处理流包装后,一次一字节，耗时：5564ms （缓冲流在内存中
                                                                        //创建默认8192缓冲区大小）
              BufferedOutputStream bos = new BufferedOutputStream(fos);
              ){

            // 2.使用输入流和输出流完成文件复制
            int n; // 定义一个中转站，就是一个字节

            // 先读取一个字节
            n = bis.read();
            while(n != -1){ // n == -1 代表读到了文件的末尾
                // 写一个字节
                bos.write(n);
                //System.out.println(n);
                // 再读一个字节
                n = bis.read();

            }


        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
            long endTime = System.currentTimeMillis();
            System.out.println("复制完毕！耗时：" + (endTime - startTime) + "ms");



    }




}
