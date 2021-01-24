package com.glod.annotationAndReflection;

import java.lang.reflect.Field;

/**
 * @description: 测试注解(使用注解加反射动态打印SQL语句）
 * @author: Glod
 * @date: 2021/1/24
 */
public class TestClient {
    private final static String name = "jinyan";
    private final static  int age = 25;
    //private final static String COLUMANNOTATION = "COLUMANNOTATION";

    public static void main(String[] args) {
        // select * from person where age = 25 and name = 'jinyan';

        try {
            Class<Person> clazz = (Class<Person>) Class.forName("com.glod.annotationAndReflection.Person");
//            System.out.println(clazz.getName());
//            System.out.println(clazz.getSimpleName());

           // 类上面是否有这个注解存在
            boolean izAnnotation = clazz.isAnnotationPresent(Table.class);
            if (!izAnnotation){
                return;
            }

            StringBuilder sb = new StringBuilder();

            Table table = clazz.getAnnotation(Table.class);
            // 获取Table的value值
            System.out.println("类上的@Person注解的值: " + table.value()); //peron
            sb.append("SELECT * FROM " + table.value() + " WHERE ");


            // 获取所有声明的方法
//            Field[] declaredFields = clazz.getDeclaredFields();
//            for (Field fields : declaredFields){
//                // System.out.println(fields);
//                // 方法上是否有这个注解存在
//                if (fields.isAnnotationPresent(Colum.class)){
//                    Colum colum = fields.getAnnotation(Colum.class);
//                    System.out.println("列上的@colum注解的值: " + colum.value());
//                   // sb.append(colum.value() +  )
//
//                }
//            }

            // Person > name
            Field field = clazz.getDeclaredField("name");
            if (field.isAnnotationPresent(Colum.class)){
                Colum colum = field.getAnnotation(Colum.class);
                sb.append(colum.name() + " = " + colum.value());
            }

            sb.append(" AND ");

            // Person > age
            field = clazz.getDeclaredField("age");
            if (field.isAnnotationPresent(Colum.class)){
                Colum colum = field.getAnnotation(Colum.class);
                sb.append(colum.name() + " = " + Integer.valueOf(colum.value()));
            }

            System.out.println(sb.toString());
            // clazz.getDeclaredMethod();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }catch (NoSuchFieldException e){
            e.printStackTrace();
        }
    }
}
