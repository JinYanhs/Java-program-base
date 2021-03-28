package com.glod.callback.callBackSync;



/**
 * @description: 发起回调的类
 * @author: Glod
 * @date: 2021/3/28
 */
public class InterfaceTest {
    CallbackInterface callbackInterFace = null;

    public void setCallbackInterFace(CallbackInterface callbackInterFace) {
        this.callbackInterFace = callbackInterFace;
    }

    public void handleThings() throws InterruptedException {
        System.out.println("做一些事");
        Thread.sleep(3000);
        callbackInterFace.call("同步回调");
    }
}
