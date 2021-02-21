package com.glod.IO;

import java.io.Serializable;

/**
 * @description: 学生pojo
 * @author: Glod
 * @date: 2021/2/21
 */
public class Student implements Serializable {

    private static final long serialVersionUID = 6425515016538091479L;

    private transient String name; // transient 临时的 persistance 持久的
    private Integer number;
    private Integer age;

    @Override
    public String toString() {
        return "Student{" +
                "name='" + name + '\'' +
                ", number=" + number +
                ", age=" + age +
                '}';
    }

    public Student(String name, Integer number, Integer age) {
        this.name = name;
        this.number = number;
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getNumber() {
        return number;
    }

    public void setNumber(Integer number) {
        this.number = number;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }
}
