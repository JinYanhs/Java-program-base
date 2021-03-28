package com.glod.callback.callBackAsync;

/**
 * @description: 入口类
 *           回调：回调是一种双向的调用模式，也就是说，被调用的接口被调用时也会调用对方的接口，例如A要调用B，B在执行完又要调用A。
 * @author: Glod
 * @date: 2021/3/28
 */
public class Main implements CallbackInterFace{
    public static void main(String[] args) {
        System.out.println("-------接口使用测试--------");
        InterfaceTest it = new InterfaceTest();
        // 调用InterfaceTest的handleThings方法，并传递Main的实例
        it.handleThings(new Main());
        System.out.println("------异步回调测试------");
    }
    @Override
    public void sendMessage(UserInfo u) {
        // 对回调线程传入的参数msg,做进一步增强处理等，但是真正执行在InterfaceTest类中
        System.out.println("接口回调成功，利用 " + u + " 做一些事情");
    }
}
