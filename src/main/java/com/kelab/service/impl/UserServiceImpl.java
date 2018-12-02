package com.kelab.service.impl;

import com.kelab.dao.UserDao;
import com.kelab.pojo.User;
import com.kelab.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service("userService")
@Transactional
public class UserServiceImpl implements UserService {
    @Autowired
    private UserDao userDao;

    @Override
    public List<User> findAllUser() {
        List<User> userList = this.userDao.findAllUser();
        return userList;
    }

    @Override
    public User findUserById(Integer id) {
        User user = this.userDao.findUserById(id);
        return user;
    }

    @Override
    public int addUser(User user) {
        return this.userDao.addUser(user);
    }
}
