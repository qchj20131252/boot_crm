package com.kelab.controller;


import com.kelab.pojo.User;
import com.kelab.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class UserController {
    @Autowired
    private UserService userService;

    @RequestMapping("/findUserById")
    public void findUserById(Integer id) {
        User user = this.userService.findUserById(id);
        System.out.println(user);
    }
}
