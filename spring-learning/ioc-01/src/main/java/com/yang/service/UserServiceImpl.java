package com.yang.service;

import com.yang.dao.UserDao;
import com.yang.dao.UserDaoImpl;
import com.yang.dao.UserDaoMysqlImpl;

public class UserServiceImpl implements UserService {
    // private UserDao userDao = new UserDaoImpl();

    private UserDao userDao;

    @Override
    public void setUserDao(UserDao userDao) {
        this.userDao = userDao;
    }

    @Override
    public void getUser() {
        userDao.getUser();
    }
}
