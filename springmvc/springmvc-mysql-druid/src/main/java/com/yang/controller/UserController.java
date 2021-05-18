package com.yang.controller;

import com.yang.pojo.User;
import com.yang.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/user")
public class UserController {
    @Autowired
    @Qualifier("userServiceTmpl")
    private UserService userService;

    @RequestMapping("/select")
    public List<User> select() {
        return userService.findAll();
    }

    @RequestMapping("/info/{id}")
    public User find(@PathVariable(name = "id") Integer id) {
        System.out.println(id);
        User user = userService.findById(id);
        return user;
    }

    public int add(User user) {
        return userService.add(user);
    }

    public int update(User user) {
        return userService.update(user);
    }

    public int delete(Integer id) {
        return userService.delete(id);
    }
}
