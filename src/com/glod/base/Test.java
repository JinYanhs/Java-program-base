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

        System.out.println("10 % 5 =" + 10 % 5);
        boolean res = 10 % 5 == 0;
        System.out.println("10 % 5 =                                                                                                                                                                                                                                                                                                                                                                                      -= 0 :" + res);

        int zeroModFive = 0 % 5;
        System.out.println("0 % 5 =" + zeroModFive);

        //java.lang.ArithmeticException: / by zero
        //int fiveModZero = 5 % 0;
        //System.out.println("5 % 0 =" + fiveModZero);


        System.out.println(32345 ^ 5);
        System.out.println((32345 ^ 9) % 10);

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
