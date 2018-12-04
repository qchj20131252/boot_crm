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

    private ObjectMapper mapper = new ObjectMapper();

    /**
     * 通过id查找用户
     * @param user
     * @return
     */
    @RequestMapping("/findUserById")
    @ResponseBody
    public User findUserById(@RequestBody User user) {
        User userResult = this.userService.findUserById(user.getId());
        return userResult;
    }

    /**
     * 查找所有用户
     * @return json字符串数组
     * @throws JsonProcessingException
     */
    @RequestMapping(value = "/findAllUser",produces = "text/plain;charset=utf-8")
    @ResponseBody
    public String findAllUser() throws JsonProcessingException {
        List<User> userList = this.userService.findAllUser();
        ObjectMapper mapper = new ObjectMapper();
        String users = mapper.writeValueAsString(userList);
        return users;
    }

    /**
     * 添加用户
     * @param user 用户对象
     * @return json字符串
     * @throws JsonProcessingException
     */
    @RequestMapping("/addUser")
    @ResponseBody
    public String addUser(@RequestBody User user) throws JsonProcessingException{
        int rows = this.userService.addUser(user);
        String result = "FAIL";
        if (rows > 0) {
            result = "OK";
        }
        return mapper.writeValueAsString(result);
    }

    @RequestMapping("/updateUser")
    @ResponseBody
    public String updateUser(@RequestBody User user) throws JsonProcessingException {
        int rows = this.userService.updateUser(user);
        String result = "FAIL";
        if (rows > 0) {
            result = "OK";
        }
        return mapper.writeValueAsString(result);
    }

    @RequestMapping("/deleteUser")
    @ResponseBody
    public String deleteUser(@RequestBody User user) throws JsonProcessingException {
        int rows = this.userService.deleteUser(user.getId());
        String result = "FAIL";
        if (rows > 0) {
            result = "OK";
        }
        return mapper.writeValueAsString(result);
    }
}
