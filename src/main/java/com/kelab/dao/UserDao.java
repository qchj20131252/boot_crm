package com.kelab.dao;

import com.kelab.pojo.User;

public interface UserDao {
    public User findUserById(Integer id);
}
