package com.yang.service;

import com.yang.dao.UserDao;

public interface UserService {
    void getUser();

    void setUserDao(UserDao userDao);
}
