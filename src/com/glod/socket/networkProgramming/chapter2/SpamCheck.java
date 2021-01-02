package com.glod.socket.networkProgramming.chapter2;

import java.net.InetAddress;
import java.net.UnknownHostException;

/**
 * @description: SpamCheck判断特定IP地址是否为垃圾邮件发送者的IP
 * @author: 金研
 * @date: 2021/1/2
 */
public class SpamCheck {
    public static final String BLACKHOLE = "sbl.spamhaus.org";

    public static void main(String[] args)throws Exception {
        for (String arg : args){
            if (isSpammer(arg)){
                System.out.println(arg + "是已知的垃圾邮件发送者");
            }else{
                System.out.println(arg + "是已知的合法邮件发送者");
            }
        }
    }

    private static boolean isSpammer(String str)throws Exception{
        try{
            InetAddress address = InetAddress.getByName(str);
            // 获取主机的Ip地址
            byte[] quad = address.getAddress();
            // 黑洞列表
            String query = BLACKHOLE;

            //反转这个IP地址的字节，并添加黑洞服务的域
            //例如对于IP地址"108.33.56.27"
            //query的最终取值为”27.56.33.108.sbl.spamhaus.org"
            for (byte octet : quad){
                int unsignedByte = octet < 0 ? octet + 256 : octet;
                query = unsignedByte + "." + query;
            }

            //查找这个地址
            InetAddress.getByName(query);

            return true;
        }catch (UnknownHostException e){
            return false;
        }
    }


}
