package com.glod.callback.callBackAsync;

/**
 * @description: 发起回调的类
 * @author: Glod
 * @date: 2021/3/28
 */
public class InterfaceTest {
    // 这里Main实例向上转型，接口变量引用了Main实例
    public void handleThings(CallbackInterFace example) {

        new Thread(new Runnable() {
            @Override
            public void run() {
                System.out.println("-----做一些事------");
                try {
                    Thread.sleep(3000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                UserInfo u = new UserInfo();
                u.setUserName("晶晶");
                u.setAge(18);
                u.setDes("蛮漂亮的");
                 //回调接口方法 层间协作，完成本层工作之外，将进行回调，将这个数据交给上层应用层来进一步处理，普遍存在与分层的数据通信中
                example.sendMessage(u);
            }
        }).start();

    }

}
