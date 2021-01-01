package com.glod.base;

public class Test {

    /**
     *  位运算
     */
    @org.junit.jupiter.api.Test
    public void bitOperation(){
        System.out.println(20 * 16 == 20 << 4);
        System.out.println(20 << 4); // equals 20 乘 16（2的4次方）
        System.out.println();
    }
}
