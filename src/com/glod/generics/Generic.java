package com.glod.generics;/**

/**
 * @Description 泛型类
 *
 * @Author glod
 * @Date 2021/5/7
 * @Modifier
 * @Modified Date
 * @Version 1.0
 */

import sun.plugin2.message.ShowDocumentMessage;

/**
 * 泛型的类型参数只能是类类型，不能是简单类型。
 不能对确切的泛型类型使用instanceof操作。如下面的操作是非法的，编译时会出错。
 　　if(ex_num instanceof Generic<Number>){ }
 *
 * 此处T可以随便写为任意标识，常见的如T、E、K、V等形式的参数常用于表示泛型
 * 在实例化泛型类时，必须指定T的具体类型
 */
public class Generic<T> {
    // key这个成员变量的类型为T,T的类型由外部指定
    private T key;
    // 泛型构造方法形参key的类型也为T，T的类型由外部指定
    public Generic(T key) {
        this.key=key;
    }
    //泛型方法getKey的返回值类型为T，T的类型由外部指定
    public T getKey() {
        return key;
    }

    // 泛型的类型参数只能是类类型（包括自定义类），不能是简单类型
    // 传入的实参类型需与泛型的类型参数类型相同，即为Integer.
    public static void main(String[] args) {
       Generic<Integer> genericInteger = new Generic<Integer>(123456);

       // 传入的实参类型需与泛型的类型参数类型相同，String
        Generic<String> stringGeneric = new Generic<String>("key_value");
//        System.out.println("泛型测试，key is " + genericInteger.getKey());
//        System.out.println("泛型测试，key is  " + stringGeneric.getKey());


        /**
         * Ingeter是Number的一个子类,逻辑上类似于Generic<Number>和Generic<Ingeter>是否可以看成具有父子关系的泛型类型呢？
         */
        Generic<Integer> gInteger = new Generic<Integer>(123);
        Generic<Number> gNumber = new Generic<Number>(456);
        showKeyValue(gNumber);
        // 编译报错， showKeyValue这个方法编译器会为我们报错：Generic<java.lang.Integer>
        // cannot be applied to Generic<java.lang.Number>
        // showKeyValue(Generic<Number> obj)改为 showKeyValue(Generic< ? extends Number> obj)编译通过
         showKeyValue(gInteger);
    }

    public static void showKeyValue(Generic< ? extends Number> obj){
        System.out.println("泛型测试，key value is " + obj.getKey());
    }

}
