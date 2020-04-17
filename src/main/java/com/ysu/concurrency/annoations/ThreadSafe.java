package com.ysu.concurrency.annoations;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/*
* 线程安全类
* */
@Target(ElementType.TYPE)//注解作用的目标
@Retention(RetentionPolicy.SOURCE)//注解存在的范围
public @interface ThreadSafe {

    String value() default "";
}
