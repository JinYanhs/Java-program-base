package com.glod.annotationAndReflection;

import com.sun.xml.internal.bind.v2.schemagen.xmlschema.NoFixedFacet;

import javax.management.ReflectionException;
import java.lang.reflect.*;

/**
 * @description:
 * @author: Glod
 * @date: 2021/1/24
 */
public class LearnClass {


    @SuppressWarnings("all")
    public static void main(String[] args) {
        /**
         * .setAccessible(true) 有安全检查7倍相对原始创建速度
         *                      无安全检查11倍
         */

        {
            try {
                Class<Person> clazz1 = (Class<Person>) Class.forName("com.glod.annotationAndReflection.Person");
                Class clazz2 = (Class<Person>) Class.forName("com.glod.annotationAndReflection.Person");

                Class clazz3 = Person.class;

                Person person = new Person();
                Class clazz4 = person.getClass();

                Class clazz5 = int.class;
                Class clazz6 = Integer.class;
                System.out.println("int.class == Integer.class : " + (clazz5 == clazz6)); // false

                System.out.println("clazz1 == clazz2 :" + (clazz1 == clazz2));
                System.out.println("clazz1 == clazz3 :" + (clazz1 == clazz3));
                System.out.println("clazz2 == clazz4 :" + (clazz2 == clazz4));
                System.out.println(clazz1.hashCode() + "\t" + clazz2.hashCode());


                // 根据字节码动态创建Person对象
                Person person1 = clazz1.newInstance(); //不加泛型，这块会提示Object对象转型错误
                person1.setName("jinyan");
                System.out.println(person1.getName());

                // 反射获取构造器
                Constructor<Person>[] conArr = (Constructor<Person>[]) clazz1.getConstructors();
                for (Constructor<Person> c : conArr){
                    System.out.println(c);
                }

                Constructor<Person> constructor = clazz1.getConstructor(String.class,Integer.class);
                Person person2 = constructor.newInstance("jyy",25);
                System.out.println(person2.getName() + "\t" + person2.getAge());

                // Field feild = clazz1.getField("name"); // getFiled()只能获取公共属性
                Field field2 = clazz1.getDeclaredField("name");
                field2.setAccessible(true); // 关闭类型检查，否则不可为私有成员设置变量
                field2.set(person2,"金研");
                System.out.println(field2.get(person2) + "\t name = " + person2.getName());


                Method method = clazz1.getDeclaredMethod("getName",null);
                String name = (String) method.invoke(person2,null);
                System.out.println("person2的名字：" + name);

            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }catch (InstantiationException e ){
                e.printStackTrace();
            }catch (IllegalAccessException e){
                e.printStackTrace();
            }catch (NoSuchMethodException e){
                e.printStackTrace();
            }catch (InvocationTargetException e){
                e.printStackTrace();
            }catch (NoSuchFieldException e ){
                e.printStackTrace();
            }
        }
    }

}
