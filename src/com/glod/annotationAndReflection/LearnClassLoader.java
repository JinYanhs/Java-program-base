package com.glod.annotationAndReflection;

/**
 * @description: 类加载机制
 * @author: Glod
 * @date: 2021/1/24
 */
public class LearnClassLoader {
    /**
     *  JVM参数固定格式：-XX:+<option>开启某个虚拟机参数
     *  类加载轨迹参数：-XX:+TraceClassLoading
     *
     *  七种类的主动使用方式
     *  1.创建类的实例
     *  2.Class.forName("包名类名")
     *  3.调用类的静态方法
     *  4.访问某个类或接口静态变量或者赋值
     *  5.初始化类的子类
     *  6.类和接口初始化一次
     *  7.被标记为启动类的类（main)
     *
     *  被动使用：处理以上其中不会初始化类
     */
    public static int a = 15;
    static {
        System.out.println("static块 " + a);
    }

    public static void main(String[] args) {
        System.out.println("------------------main--------------");
        System.out.println(LearnClassLoader.a);
    }

    // -------------------加载--------------
    // 1.找到字节码文件，如何找到
    // 2.把字节码文件生成Class对象

    // --------------------链接------------
    // 1.字节码进行安全检查
    // 2.int a 分配4个字节的内存，a = 0
    // 3.把类中的符号引用转换为直接引用

    // ---------------------初始化-----------
    // 类的静态变量赋予正确的初始值
    // a = 15

}
