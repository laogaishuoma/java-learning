package com.yang;

import javassist.*;

import java.io.IOException;

public class Demo {
    public static void main(String[] args) throws NotFoundException, CannotCompileException, IOException {
        /**
         * 修改类文件:
         * 1. 获取ClassPool对象: ClassPool.getDefault(), 该对象搜索CtClass对象的路径为系统默认路径
         * 2. 从ClassPool中获取一个key为com.yang.shape.Rectangle的CtClass对象
         * 3. 修改该对象使其继承自com.yang.shape.Point对象
         * 4. 保存该对象到一个class文件
         */
        ClassPool pool = ClassPool.getDefault();
        CtClass cc = pool.get("com.yang.shape.Rectangle");
        cc.setSuperclass(pool.get("com.yang.shape.Point"));
        cc.writeFile();
        System.out.println("----------" + cc);

        /**
         * 创建新类
         */
        CtClass cc2 = pool.makeClass("Point");
        System.out.println("----------" + cc2);

        /**
         * 创建新接口
         */
        CtClass hello = pool.makeInterface("Hello");
        System.out.println("----------" + hello);

        pool.insertClassPath(new ClassClassPath(Demo.class.getClass()));

        System.out.println(pool);
    }
}