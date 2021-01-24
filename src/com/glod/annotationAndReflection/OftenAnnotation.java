package com.glod.annotationAndReflection;

import java.util.List;

/**
 * @description: 常用注解
 * @author: Glod
 * @date: 2021/1/24
 */
public class OftenAnnotation {
    public static void main(String[] args) {
        int addResult = add(11,6);
        System.out.println(addResult);

    }

    /**
     * @Retention:三种保留策略，分别为SOURCE、CLASS、RUNTIME
     * @Target: 注解的目标对象
     * @Documented: 生成文档时候，也会把注解信息加进去否则没有
     *
     * SOURCE:在源代码有效，针对编译器
     */
     // @Override 注解
    @Override
    public String toString() {
        return "OftenAnnotation";
    }

    /**
     * RUNTIME:程序代码在JAVA虚拟机运行期间，也能看到禁用的标识符、注解信息
     */
    // 代码不建议试用，代码被弃用了，但可以试用
    @Deprecated
    static int add(int a,int b){
        return a + b;
    }

    /**
     * 镇压警告注解
     * 保留策略：SOURCE级别
     * all: 表示镇压所有警告
     */
    @SuppressWarnings("all")
    void sayHelllo(){
        List list;
        List list2;
    }

}
