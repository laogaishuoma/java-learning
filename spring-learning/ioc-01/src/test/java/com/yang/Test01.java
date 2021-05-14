package com.yang;

import com.yang.dao.UserDaoMysqlImpl;
import com.yang.service.UserService;
import com.yang.service.UserServiceImpl;

public class Test01 {
    public static void main(String[] args) {
        UserService userService = new UserServiceImpl();
        userService.setUserDao(new UserDaoMysqlImpl());
        userService.getUser();
    }
}
