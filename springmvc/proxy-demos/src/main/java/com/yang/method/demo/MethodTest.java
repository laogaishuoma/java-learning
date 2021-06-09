package com.yang.method.demo;

import com.yang.method.service.HelloService;
import com.yang.method.service.impl.HelloServiceImpl;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class MethodTest {
    public static void main(String[] args) throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        HelloService target = new HelloServiceImpl();
        /**
         * 通过反射机制获取目标对象的Method实例
         */
        Method method = HelloService.class.getMethod("sayHello", String.class);


        /**
         * invoke是Method中的一个方法，表示执行方法的调用。
         * 参数:
         * 1. Object: 表示对象，要执行这个对象的方法。
         * 2. Object... args: 表示方法执行时的参数值
         *
         * 返回值:
         * Object: 方法执行后的返回值
         * 表达的意识就是执行target对象的sayHello方法，参数是world。
         */
        method.invoke(target, "world");
    }
}
