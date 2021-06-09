package com.yang.method.service.impl;

import com.yang.method.service.HelloService;

public class HelloServiceImpl implements HelloService {
    @Override
    public void sayHello(String name) {
        System.out.println("Hello " + name);
    }
}
