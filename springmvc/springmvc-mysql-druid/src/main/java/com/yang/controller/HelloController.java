package com.yang.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/hello")
public class HelloController {
    @RequestMapping("/t1")
    public String test(Model model) {
        model.addAttribute("msg", "Hello SpringMVC!");
        return "test";
    }
}