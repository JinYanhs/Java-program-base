package com.glod.base;

public class Test {

    /**
     *  位运算
     */
    @org.junit.Test
    public  void bitOperation(){
        System.out.println(20 * 16 == 20 << 4);
        System.out.println(20 << 4); // equals 20 乘 16（2的4次方）
        System.out.println(2 << 24); // 33554432
    }

    @org.junit.Test
    public void swapNum(){
        // 异或换位
        int x = 1;
        int y = 2;
        System.out.println("交换前 ：x = " + x + " y = " + y);

        x = x ^ y;
        y = x ^ y;
        x = x ^ y;
        System.out.println("交换后 ：x = " + x + " y = " + y);
    }
}
