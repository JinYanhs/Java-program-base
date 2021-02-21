package com.glod.IO.ios7;

import java.io.*;

/**
 * @description: 复制一个文件和子文件夹
 * @author: Glod
 * @date: 2021/2/21
 */
public class TestDirCopy3 {
    public static void main(String[] args) {
        String sourceFile = "E:/Java";
        String destFile = "E:/Java/testFile/Java2";
        copyDir(sourceFile,destFile);
    }

    /**
     *
     * @param sourceDir
     * @param destDir
     */
    public static void copyDir(String sourceDir,String destDir){
        // 源文件夹必须存在
        File file = new File(sourceDir);
        if (!file.exists()){
            System.err.println("源文件夹不存在" + sourceDir);
            return;
        }
        // 目的文件夹要创建
        File file2 = new File(destDir);
        if (!file2.exists()){
            file2.mkdirs();
        }
        // 获取源文件夹下所有的文件和子文件夹
        File files[] = file.listFiles();
        for (File f : files){
            // 如果文件就复制
            if (f.isFile()){
               copyFile(sourceDir + "/" + f.getName(),destDir + "/" + f.getName() );
            }
            // 如果文件夹复制
            if (f.isDirectory()){
                copyDir(sourceDir + "/" + f.getName(),destDir + "/" + f.getName());
            }
        }
    }

    /**
     *  复制一个文件
     * @param sourceFile 源文件
     * @param destFile 目的文件
     */
    public static void copyFile(String sourceFile, String destFile) {

        long startTime = System.currentTimeMillis();

        try (  // 实例化对象放入这个里面都不用手动关了，实现AutoCloseable接口的对象才可放入
               InputStream fis = new FileInputStream(sourceFile); // 单纯节点流一次一字节100m视频复制，。。。。。。。。。。。。。
               OutputStream fos = new FileOutputStream(destFile);

               // 处理流关了，节点流也会关闭，不关闭流的话，会导致部分数据写入不到目标文件中
               BufferedInputStream bis = new BufferedInputStream(fis); // 处理流包装后,一次一字节，耗时：5564ms （缓冲流在内存中
               //创建默认8192缓冲区大小）
               BufferedOutputStream bos = new BufferedOutputStream(fos);
        ) {

            // 2.使用输入流和输出流完成文件复制
            byte[] buf = new byte[1024];

            int len = bis.read(buf);
            while (len != -1) { // n == -1 代表读到了文件的末尾
                // 写一个字节
                bos.write(buf, 0, len);
                //System.out.println(n);
                // 再读一个字节
                len = bis.read(buf);

            }


        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        long endTime = System.currentTimeMillis();
        System.out.println("复制" + new File(sourceFile).getName() + "完毕！耗时：" + (endTime - startTime) + "ms");


    }
}
