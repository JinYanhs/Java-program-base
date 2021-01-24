package com.glod.annotationAndReflection;

/**
 * @description: 自定义注解@Table使用
 * @author: Glod
 * @date: 2021/1/24
 */
/*  注释不能其他程序被读取 */
@Table("person")
public class Person {
    // 注解的设计目标： 对类做出解释，被其他程序读取，被反射读取（注解配合反射才有意义）

    @Colum(value = "jinyan", name = "name" )
    private String name;
    @Colum(value = "25", name = "age" )
    private Integer age;

    public void setName(String name) {
        this.name = name;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public Integer getAge() {
        return age;
    }

    public Person(Integer age) {
        this.age = age;
    }

    public Person(String name) {
        this.name = name;
    }

    public Person(String name, Integer age) {
        this.name = name;
        this.age = age;
    }

    public Person() {
    }
}
