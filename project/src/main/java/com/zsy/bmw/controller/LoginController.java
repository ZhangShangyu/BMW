package com.zsy.bmw.controller;

import com.zsy.bmw.model.User;
import com.zsy.bmw.service.UserService;
import com.zsy.bmw.utils.Constant;
import com.zsy.bmw.utils.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by MAC on 21/04/2017.
 */
@RestController
public class LoginController {

    @Autowired
    private UserService userService;

    @RequestMapping(value = "/register")
    public Result Register(@RequestBody User user) {
        if (user.getName() == null || user.getPassword() == null) {
            return new Result(Constant.ERROR_CODE1, Constant.PARAM_ERROR);
        }
        if (userService.findUserByName(user.getName()) != null) {
            return new Result(Constant.ERROR_CODE2, Constant.NAME_DUPLICATE);
        }
        userService.insertUser(user);
        return new Result(Constant.OK_CODE, Constant.OK);
    }

    @RequestMapping(value = "/login")
    public Result Login(@RequestBody User user) {
        if (user.getName() == null || user.getPassword() == null) {
            return new Result(Constant.ERROR_CODE1, Constant.PARAM_ERROR);
        }
        User _user = userService.getUserByNameAndPassword(user);
        if (_user == null) {
            return new Result(Constant.ERROR_CODE2, Constant.LOGIN_ERROR);
        }
        Result result = new Result(Constant.OK_CODE, Constant.OK);
        result.setData(_user);
        return result;
    }
}
