package com.yang.controller;

import com.yang.annotation.UserId;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/a1")
public class HelloController {
    @RequestMapping("/hello")
    public String hello(Model model) {
        model.addAttribute("msg", "Hello, SpringMVC-Annotations");
        return "hello";
    }

    @RequestMapping("/argument")
    public String argument(@UserId Integer userId) {
        System.out.println("userId: " + userId);
        return "hello";
    }
}
