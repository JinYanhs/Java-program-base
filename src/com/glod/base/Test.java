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

        // 异常 XOR exclusive OR
        System.out.println("-----------------异或[begin]----------------------");
        System.out.println("任何数和零异或 : " + (7 ^ 0)); // 任何一个数与自身异或等于本身
        System.out.println("一个值与自身异或 ：" + (6 ^ 6)); // 0
        boolean resXOR = (8 ^ 3) == (3 ^ 8);
        System.out.println("对称性 ： " + resXOR );
        // 结合性 x ^ (y ^ z) = (x ^ y) ^ z
        boolean resXOR2 = (2 ^ (3 ^ 5)) == ((2 ^ 3) ^ 5);
        System.out.println("结合性 ： " + resXOR2);

        /**
         *  原理
         *
         *  (x ^ y) ^ y
         *   = x ^ (y ^ y)
         *   = x ^ 0
         *   = x
         *
         *   应用
         */


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
