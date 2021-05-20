package com.yang.controller;

import com.yang.annotation.Autowired;
import com.yang.service.UserService;

public class UserController {
    @Autowired
    UserService userService;

    public UserService getUserService() {
        return userService;
    }

//    public void setUserService(UserService userService) {
//        this.userService = userService;
//    }
}
