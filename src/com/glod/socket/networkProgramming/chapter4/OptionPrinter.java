package com.glod.socket.networkProgramming.chapter4;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.SocketOption;
import java.nio.channels.*;

/**
 * @description: 打印处每一种类型的通道支持的所有选项
 * @author: Glod
 * @date: 2021/1/16
 */
public class OptionPrinter {
    public static void main(String[] args)throws IOException {
      printOptions(SocketChannel.open());
//      printOptions(ServerSocketChannel.open());
//      printOptions(AsynchronousServerSocketChannel.open());
//      printOptions(AsynchronousSocketChannel.open());
//      printOptions(DatagramChannel.open());

    }

    private static void printOptions(NetworkChannel channel)throws IOException{
        System.out.println(channel.getClass().getSimpleName()
        + "supports:");
        for (SocketOption<?> option : channel.supportedOptions()){
            try{
                System.out.println(option.name() + ":"
                + channel.getOption(option));
            }catch (AssertionError e){
                e.printStackTrace();
            }

        }
        System.out.println();
        channel.close();
    }
}
