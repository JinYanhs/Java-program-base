package com.glod.annotationAndReflection;

import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.FIELD;

/**
 * @description: 列注解
 * @author: Glod
 * @date: 2021/1/24
 */
@Documented
@Retention(RetentionPolicy.RUNTIME)
// @Target 注解的注解称为元注解
@Target({FIELD})
public @interface Colum {

    String value();

    String name();

}
