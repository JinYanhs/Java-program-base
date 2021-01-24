package com.glod.IO;

import java.io.File;
import java.util.Arrays;

/**
 * @description: 列出文件列表
 * @author: Glod
 * @date: 2021/1/23
 */
public class DirList {
    public static void main(String[] args) {
        File path = new File(".");
        String[] list;
        if (args.length == 0){
            list = path.list();
        }else{
            list = path.list(new DirFilter(args[0]));
        }

        Arrays.sort(list,String.CASE_INSENSITIVE_ORDER);
        for (String dirItem : list){
            System.out.println(dirItem);
        }
    }
}
