package com.kelab.dao;

import com.kelab.pojo.User;

import java.util.List;

public interface UserDao {
    public int addUser(User user);
    public int deleteUser(Integer id);
    public int updateUser(User user);
    public List<User> findAllUser();
    public User findUserById(Integer id);

}
