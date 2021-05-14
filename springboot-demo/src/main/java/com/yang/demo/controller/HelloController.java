package com.yang.demo.controller;

import com.yang.demo.annotation.Login;
import com.yang.demo.annotation.Param;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/hello")
public class HelloController {

    @GetMapping("/arg")
    @Login
    public Map<String, Object> arg(@Param(required = true) String a) {
        Map map = new HashMap<>();
        map.put("time", new Date().toString());
        map.put("a", a);
        return map;
    }
}
