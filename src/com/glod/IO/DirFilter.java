package com.glod.IO;

import java.io.File;
import java.io.FilenameFilter;
import java.util.regex.Pattern;

/**
 * @description: 文件过滤器
 * @author: Glod
 * @date: 2021/1/23
 */
public class DirFilter implements FilenameFilter {
    private Pattern pattern;
    public DirFilter(String regex){
        pattern = Pattern.compile(regex);
    }
    @Override
    public boolean accept(File dir, String name) {
        return pattern.matcher(name).matches();
    }
}
