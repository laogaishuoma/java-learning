package com.yang.demo.annotation;

import java.lang.annotation.*;
/**
 * @author qiaoguoqiang
 */
@Target({ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Inherited
public @interface Login {
    boolean required() default true;
}
