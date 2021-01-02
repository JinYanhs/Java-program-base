package com.glod.socket.networkProgramming.chapter2;

import java.net.InetAddress;

/**
 * @description: InetAddress 地址类的用法
 * @author: 金研
 * @date: 2021/1/2
 */
public class InetAddressTest {
    public static void main(String[] args)throws Exception {
        // 获取主机名的两种方法
        // getHostName():先从DNS缓存查找，如果没有再从DNS域名服务器查找
        System.out.println(InetAddress.getLocalHost().getHostName());
        // getCanonicalHostName():直接从DNS域名服务器查找
        System.out.println(InetAddress.getLocalHost().getCanonicalHostName());

    }
}
