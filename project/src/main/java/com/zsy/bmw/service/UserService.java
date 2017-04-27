package com.zsy.bmw.service;

import com.zsy.bmw.dao.UserMapper;
import com.zsy.bmw.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by MAC on 26/04/2017.
 */

@Service
public class UserService {

    @Autowired
    private UserMapper userMapper;

    public User findUserByName(String name) {
        return userMapper.findUserByName(name);
    }

    public User getUserByNameAndPassword(User user) {
        return userMapper.getUserByNameAndPassword(user);
    }

    public void insertUser(User user) {
        if (user.getRoleId() == null) {
            user.setRoleId(1);
        }
        userMapper.insert(user);
    }
}
