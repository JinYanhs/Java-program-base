package com.glod.base;

import com.glod.collect.set.Student;

import java.util.Objects;

public class Test {
    // 程序要严谨，优雅，易复用

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
        System.out.println("任何数和零异或 : " + (7 ^ 0)); // 任何一个数与零异或等于本身
        System.out.println("一个值与自身异或 ：" + (6 ^ 6)); // 0
        boolean resXOR = (8 ^ 3) == (3 ^ 8);
        System.out.println("对称性 ： " + resXOR );
        // 结合性 x ^ (y ^ z) = (x ^ y) ^ z
        boolean resXOR2 = (2 ^ (3 ^ 5)) == ((2 ^ 3) ^ 5);
        System.out.println("结合性 ： " + resXOR2);
        // 一个偶数^1,那么答案是偶数+1.如果是一个奇数^1,那么答案是奇数-1 8421(偶数末尾0，算得1；奇数末尾1，算得0)
        System.out.println("2 ^ 1 = " + (2 ^ 1));
        System.out.println("3 ^ 1 = " + (3 ^ 1));
        System.out.println("4 ^ 1 = " + (4 ^ 1));
        System.out.println("5 ^ 1 = " + (5 ^ 1));
        System.out.println("6 ^ 1 = " + (6 ^ 1));
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
    public void compute(){
        System.out.println(0.1 + 0.2);  //0.30000000000000004
        System.out.println((int)98.5-98); // 0

        Student student = new Student();
        System.out.println(Objects.hash(student));

        int num = 3;
        System.out.println(Objects.hash(num));
        System.out.println(Objects.hashCode(num));

    }

    @org.junit.Test
    public void swapNum(){
        // 异或换位
        int x = 1;
        int y = 2;
        System.out.println("交换前 ：x = " + x + " y = " + y);

        x ^= y;
        y ^= x;
        x ^= y;
        System.out.println("交换后 ：x = " + x + " y = " + y);
    }
}
