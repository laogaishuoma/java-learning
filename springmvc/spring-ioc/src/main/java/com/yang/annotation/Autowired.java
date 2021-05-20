package com.yang.annotation;

import java.lang.annotation.*;

/**
 * 自定义注解@Autowired
 */
@Documented
@Inherited
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
public @interface Autowired {
}
