package com.zsy.bmw.controller;

import com.zsy.bmw.dao.UserMapper;
import com.zsy.bmw.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by MAC on 21/04/2017.
 */

@RestController
public class LoginController {

    @Autowired
    private UserMapper userMapper;

    @RequestMapping(value = "/test")
    public String Login() {
        User user = new User();
        user.setName("2");
        user.setPassword("123");
        user.setRoleId(1);
        userMapper.insert(user);
        return "hahhaaa";
    }
}
