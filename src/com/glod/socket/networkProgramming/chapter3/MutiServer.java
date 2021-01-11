package com.glod.socket.networkProgramming.chapter3;

/**
 * @description: 并发SocketServer
 * @author: 金研
 * @date: 2021/1/5
 */
public class MutiServer {
    /**
     *  可以用并发性能来衡量一个服务器同时响应多个客户端的能力。
     *  好的并发性能的服务器，符合以下两个条件
     *  1.能同时接收并处理多个客户连接
     *  2.对于每隔客户，都会迅速给与响应
     *  sum:服务器同时处理的客户连接数目越多，并且对每个客户做出响应的速度越快，就表明其并发性能越高
     */


    /**
     * method 1 (mutithread1)
     *  为每个客户分配一个工作线程
     *
     *  不足：服务器创建和销毁工作线程的开销（包括所花费的时间和系统资源）很大。如果服务器需要与许多客户端通信，并且每个客户的通信时间都很短，那么有可能服务器为客户
     *       创建新线程的开销比实际与客户通信的开销还要大。
     *
     */

    /**
     * method 2
     *  创建一个线程池，由其中的工作线程来为客户服务
     *
     */


    /**
     * method 3 (mutithread2) 相比 method2 更加健壮
     *  利用JDK的Java类库中现成的线程池，由它的工作线程来为客户服务
     *
     */



}