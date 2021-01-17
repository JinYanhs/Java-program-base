package com.glod.socket.networkProgramming.chapter4;

import org.junit.Test;

import java.nio.ByteBuffer;
import java.nio.CharBuffer;
import java.nio.IntBuffer;

/**
 * @description: java.nio.Buffer类测试
 * @author: Glod
 * @date: 2021/1/12
 */
public class BufferTest {
    /**
     *  buffer:IO往往比较耗时，buffer从减少物理读写次数
     *  和减少动态分配与回收内存区域的次数（缓冲区在创建时被分配内存
     *  这块内存会一直被重用）来提高IO效率
     *
     *  java.nio.Buffer是一个抽象类，不能被实例化
     */
    @org.junit.Test
    public void intBufferTest(){
         IntBuffer intBuffer = IntBuffer.allocate(10);
         intBuffer.put(1);
         intBuffer.put(2);
         intBuffer.put(3);
         intBuffer.put(4);
         intBuffer.put(5);
         intBuffer.put(6);
         intBuffer.put(7);
         intBuffer.put(8);
         // intBuffer.put(9);
         // intBuffer.put(0);
         // intBuffer.put(11); java.nio.BufferOverflowException
        System.out.println(intBuffer);

        /*
        // intBuffer.clear(); 把极限设置为容量，把位置设置为0
        // ntBuffer.flip(); 把极限设为位置，把位置设为0
        // rewind(); 不改变极限，把位置设为0 ，用来覆钙之前写入的
         */

        // 相对读，如若是没有当前位置没有放入值，则该位置为Integer默认值0
        System.out.println(intBuffer.get()); //get到position 8 但 8 处没有放入值
        System.out.println(intBuffer);
        System.out.println(intBuffer.get()); // 相对读，此时position的位置等于limit的位置，java.nio.BufferUnderflowException
        System.out.println(intBuffer.get(1)); //绝对读


    }

}
