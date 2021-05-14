package com.yang.dao;

public class UserDaoOracleImpl implements UserDao {
    @Override
    public void getUser() {
        System.out.println("Oracle获取用户信息");
    }
}
