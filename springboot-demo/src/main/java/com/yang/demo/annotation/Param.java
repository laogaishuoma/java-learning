package com.yang.demo.annotation;

import java.lang.annotation.*;

/**
 * @author qiaoguoqiang
 */
@Target({ElementType.PARAMETER})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Inherited
public @interface Param {
    boolean required() default false;
}
