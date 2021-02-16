package com.glod.IO.Files;

import java.io.File;
import java.util.Date;

/**
 * @description: 获取文件或文件夹的属性
 * @author: Glod
 * @date: 2021/2/16
 */
public class TestFile {
    public static void main(String[] args) {
//        File file = new File(
//                "E:\\Java\\practiseProcedure\\" +  // 绝对路径
//                        "Java-program-base\\address.txt"); // 反斜杠： \\ （需转义）   正斜杠：/ （无需转义）

//        File file = new File("address.txt");  // 相对路径 java-program-base目录下
        File file = new File("E:/Java");  // 相对路径 java-program-base目录下
        System.out.println(file.getAbsolutePath()); // 绝对路径
        System.out.println("文件名：" + file.getName()); // 获取文件名
        System.out.println("长度：" + file.length() + "byte");  // 长度 字节 回车占2byte?
        System.out.println("是否存在：" + (file.exists() ? "存在" : "不存在"));  // 是否存在

        System.out.println("可读：" + file.canRead()); // 可读
        System.out.println("可写：" + file.canWrite()); //可写
        System.out.println("可执行：" + file.canExecute()); // 可执行

        System.out.println("是文件：" + file.isFile()); // 是文件
        System.out.println("是文件夹：" + file.isDirectory()); // 是目录

        // 仿Windows CMD DIR命令
        System.out.println("------------------DIR--------------------");
        File[] files = file.listFiles();
        // /String[] strs = file.list();
        System.out.println("file的数量：" + files.length + "\n");
        int fileIndex = 1;
        for (File f : files) {
            System.out.print(fileIndex++ + "）" + f.getName() + "\t" + new Date(f.lastModified()).toLocaleString() + "\t");
            if (f.isDirectory()) {
                System.out.println("<DIR>");
            } else {
               // System.out.println("     " + f.length() + "byte");
                System.out.println("     " + f.length() / 1024 + "KB");
              //  System.out.println( + f.length() % 1024);
            }
        }
    }
}
