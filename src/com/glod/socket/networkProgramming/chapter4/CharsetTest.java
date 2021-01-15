package com.glod.socket.networkProgramming.chapter4;

import org.junit.jupiter.api.Test;

import java.nio.Buffer;
import java.nio.ByteBuffer;
import java.nio.CharBuffer;
import java.nio.charset.Charset;

/**
 * @description: 字符编码
 * @author: Glod
 * @date: 2021/1/15
 */
public class CharsetTest {
    /**
     *  编码：字符 —> 字节
     *  解码：字节 —> 字符
     */
     @Test
    public void charSetTest(){
         Charset charset = Charset.forName("UTF-8");
         String str = "测试用例";
         ByteBuffer strEncodingBuffer = charset.encode(str);
         // java.nio.HeapByteBuffer[pos=0 lim=12 cap=19]
         System.out.println(strEncodingBuffer);
//         while (strEncodingBuffer.hasRemaining()){
////             //System.out.println("位置：" + strEncodingBuffer.position() + ":" + strEncodingBuffer.get());
////         }

         // CharBuffer strCharBuffer = strEncodingBuffer.asCharBuffer(); 没有用charSet解码，输出为乱码
          CharBuffer strCharBuffer = charset.decode(strEncodingBuffer);
         System.out.println(strCharBuffer);
         while (strCharBuffer.hasRemaining()){
             System.out.println(strCharBuffer.get());
         }

         // 返回本地平台的默认字符编码的Charset对象
         System.out.println("本地平台默认编码：" + Charset.defaultCharset());

    }

}
