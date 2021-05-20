package com.yang;

import com.yang.annotation.Autowired;
import com.yang.controller.UserController;
import com.yang.service.UserService;
import org.junit.Test;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.stream.Stream;

public class MyTest {
    @Test
    public void test() throws NoSuchFieldException, NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        UserController userController = new UserController();
        // 获取Class
        Class clazz = userController.getClass();

        // 创建好UserService
        UserService userService = new UserService();

        // 获取类中的属性值
        Field serviceField = clazz.getDeclaredField("userService");

        // 设置访问权限: 在访问的时候如果是私有的访问类型，也可以直接进行访问
        serviceField.setAccessible(true);

        // 获取属性名称
        String name = serviceField.getName();
        name = name.substring(0, 1).toUpperCase() + name.substring(1, name.length());
        String methodName = "set" + name;

        // 获取方法对象
        Method method = clazz.getMethod(methodName, UserService.class);
        // method.setAccessible(true);
        // 执行set方法
        method.invoke(userController, userService);
        System.out.println(userController.getUserService());
    }

    @Test
    public void test2() {
        UserController userController = new UserController();
        Class clazz = userController.getClass();

        // 遍历属性字段，看是否有添加@Autowired注解的，有则设置对象，无则不处理
        Stream.of(clazz.getDeclaredFields()).forEach( field -> {
            field.setAccessible(true);
            Autowired annotation = field.getAnnotation(Autowired.class);
            if (annotation != null) {
                Class type = field.getType();
                try {
                    Object o = type.newInstance();
                    field.set(userController, o);
                } catch (InstantiationException e) {
                    e.printStackTrace();
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                }
            }
        });

        System.out.println(userController.getUserService());
    }
}
