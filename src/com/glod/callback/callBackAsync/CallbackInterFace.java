package com.glod.callback.callBackAsync;

/**
 * @description: 回调函数接口
 * @author: Glod
 * @date: 2021/3/28
 */

/**
 * 为什么要使用接口和抽象类？因为从很多角度讲，这样做符合面向对象的设计思想，比如开闭原则等。
 * 从实际开发的角度讲，Java不支持多继承，为了弥补这一点，接口的存在就极为重要，和继承不同，一个类可以实现多个接口。
 *
 * 接口的一大作用是实现回调，回调分同步回调和异步回调，区别是异步回调使用了多线程技术，当回调中有耗时操作时，就需要使用异步回调。
 *

 */
@FunctionalInterface
public interface CallbackInterFace {
    /* 回调方法 */
    void sendMessage(UserInfo msg);
}
