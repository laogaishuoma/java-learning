package com.yang.service;

import com.yang.pojo.User;

import java.util.List;

public interface UserService {
    User findById(Integer id);
    List<User> findAll();
    int add(User user);
    int update(User user);
    int delete(Integer id);
}
