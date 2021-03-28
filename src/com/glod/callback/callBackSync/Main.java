package com.glod.callback.callBackSync;

import com.glod.callback.callBackAsync.CallbackInterFace;

/**
 * @description: 入口类
 * @author: Glod
 * @date: 2021/3/28
 */
public class Main implements CallbackInterface {
    public static void main(String[] args) throws InterruptedException {

        System.out.println("-----接口使用测试----------");
        InterfaceTest it = new InterfaceTest();
        it.setCallbackInterFace(new Main());
        it.handleThings();
        System.out.println("-------同步调用测试结束------------");
    }

    @Override
    public void call(String msg) {

            System.out.println("==接口回调成功，同步调用 -》 " + msg + "over");

    }
}
