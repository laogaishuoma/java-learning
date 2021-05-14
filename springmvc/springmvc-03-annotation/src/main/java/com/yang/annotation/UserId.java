package com.yang.annotation;


import java.lang.annotation.*;

@Target({ElementType.PARAMETER})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface UserId {
    boolean required() default true;
}
