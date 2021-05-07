package com.glod.generics;

import java.util.ArrayList;
import java.util.List;

/**

import java.util.ArrayList;
import java.util.List;

/**
 * @Description 泛型特性：泛型只在编译阶段有效
 * @Author glod
 * @Date 2021/5/7
 * @Modifier
 * @Modified Date
 * @Version 1.0
 */
public class FeatrueTest {
    /**
     * 在编译之后程序会采取去泛型化的措施。也就是说Java中的泛型，只在编译阶段有效。在编译过程中，正确检验泛型结果后，会将泛型的相关信息擦出，并且在对象进入和离开方法的边界处添加类型检查和类型转换的方法。也就是说，泛型信息不会进入到运行时阶段。
     *
     * 泛型类型在逻辑上看以看成是多个不同的类型，实际上都是相同的基本类型。
     */
    public static void main(String[] args) {
        List<String> strList=new ArrayList<>();
        List<Integer> intList=new ArrayList<>();

        Class strListClass=strList.getClass();
        Class intListClass=intList.getClass();

        if (strListClass.equals(intListClass)){
            System.out.println("泛型测试，类型相同 " + intListClass);
        }
    }
}
