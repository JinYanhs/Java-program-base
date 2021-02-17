package com.glod.IO.Files;

import java.io.File;
import java.io.IOException;

/**
 * @description: 实现对文件、文件夹的创建和删除
 *               文件存在就删除，不存在就删除
 *               File不能对文件的内容进行操作，内容的操作要使用I/O流
 * @author: Glod
 * @date: 2021/2/16
 */
public class TestFile2 {
    public static void main(String[] args) {
        // 创建一个File对象指向一个文件
        File file = new File("E:/Java/testFile/Java.txt");
        // 如果文件存在就删除，不存在就创建
        if (file.exists()){
            file.delete();
            System.out.println(file.getName() + "文件删除了");
        }else{
            // 如果上级文件夹不存在，就创建
            File dir = file.getParentFile(); // 获取上级文件夹
            if (!dir.exists()){
                /**
                 * java.io.IOException: 系统找不到指定的路径。
                 * 	at java.io.WinNTFileSystem.createFileExclusively(Native Method)
                 * 	at java.io.File.createNewFile(File.java:1012)
                 * 	at com.glod.IO.Files.TestFile2.main(TestFile2.java:32)
                 *
                 * 	   Q：dir.mkdir()只能创建一级文件夹，创建目标文件上级有多级文件夹的文件会报错
                 * 	   解决：mkdirs()
                 */
                dir.mkdir(); // 创建一级文件夹   /testFile/Java.txt
  //              dir.mkdirs();  // 创建多级文件夹    a/b/c/d/testFile/Java.txt
            }

            try {

                System.out.println(file.getName() + "文件创建了");
                file.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
