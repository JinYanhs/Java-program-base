package com.glod.socket.networkProgramming.chapter3;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.concurrent.Callable;

/**
 * @description: 负责到DNS服务器中查找特定的地址，判断他是否为垃圾邮件发送者的地址 Feature接口能够保存异步运算结果
 * @author: Glod
 * @date: 2021/1/10
 */
public class LookupTask implements Callable<String> {
    public static final String BLACKHOLE = "sbl.spamhaus.org";
    String addr;
    public LookupTask(String addr){
        this.addr = addr;
    }

    @Override
    public String call() throws Exception {
        try {
            InetAddress address = InetAddress.getByName(addr);
            byte[] quad = address.getAddress(); // 获取主机上IP地址
            String query = BLACKHOLE;

            // 你只这个地址的字节，并添加黑洞服务的域
            // 例如对于IP地址为“27.56.33.108.sb1.spamhaus.org"
            for (byte octet : quad) {
                int unsignedByte = octet < 0 ? octet + 256 : octet;
                query = unsignedByte + "." + query;
            }

            //查找这个地址
            InetAddress.getByName(query);
            return addr + "是已知的垃圾邮件发送者";
        }catch (UnknownHostException e){
            return addr + "是已知的合法邮件发送者";
        }
    }
}
