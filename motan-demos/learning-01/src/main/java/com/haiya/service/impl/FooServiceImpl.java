package com.haiya.service.impl;

import com.haiya.service.FooService;

public class FooServiceImpl implements FooService {
    @Override
    public String hello(String name) {
        System.out.println(name + " invoked rpc service");
        return "hello " + name;
    }
}
