package com.glod.callback.callBackAsync;

/**
 * @description: 第二种类型入口类
 * @author: Glod
 * @date: 2021/3/28
 */
public class Main2{
    public static void main(String[] args) {
        System.out.println("-------接口使用测试--------");
        InterfaceTest it = new InterfaceTest();
        // 调用InterfaceTest的handleThings方法，并使用实现了CallbackInterface接口的匿名内部类
        // 在该匿名内部类中重写接口方法
        it.handleThings(msg -> {

                System.out.println("接口回调成功，利用 " + msg + " 做一些事情");
        });
        System.out.println("------异步回调测试------");
    }

}
