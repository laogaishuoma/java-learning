package com.yang.dao;

import com.yang.pojo.User;

import java.util.List;

public interface UserMapper {
    User findById(Integer id);
    List<User> findAll();
    int add(User user);
    int update(User user);
    int delete(Integer id);
}
