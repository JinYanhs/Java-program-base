package com.glod.annotationAndReflection;

import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

// 静态的方式进行导入
import static java.lang.annotation.ElementType.*;
import static java.lang.annotation.RetentionPolicy.CLASS;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

/**
 * @description: 自定义注解,表注解
 * @author: Glod
 * @date: 2021/1/24
 */
@Documented
@Retention(RUNTIME)
@Target({TYPE,FIELD})
public @interface Table {

    // 参数类型、参数名，只有一个参数时，默认名称是value
    String value();
}
