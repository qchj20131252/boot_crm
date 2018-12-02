package com.kelab.controller;


import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.kelab.pojo.User;
import com.kelab.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.swing.plaf.synth.SynthOptionPaneUI;
import java.util.List;

@Controller
public class UserController {
    @Autowired
    private UserService userService;

    @RequestMapping("/findUserById")
    @ResponseBody
    public User findUserById(@RequestBody User user) {
        User userResult = this.userService.findUserById(user.getId());
        return userResult;
    }

    @RequestMapping(value = "/findAllUser",produces = "text/plain;charset=utf-8")
    @ResponseBody
    public String findAllUser() throws JsonProcessingException {
        List<User> userList = this.userService.findAllUser();
        ObjectMapper mapper = new ObjectMapper();
        String users = mapper.writeValueAsString(userList);
        return users;
    }

    @RequestMapping("/addUser")
    @ResponseBody
    public String addUser(@RequestBody User user) throws JsonProcessingException{
        System.out.println(user);
        int rows = this.userService.addUser(user);
        ObjectMapper mapper = new ObjectMapper();
        String result = "FAIL";
        if (rows > 0) {
            result = "OK";
        }
        return mapper.writeValueAsString(result);
    }
}
