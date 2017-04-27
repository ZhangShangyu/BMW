package com.zsy.bmw.dao;

import com.zsy.bmw.model.User;
import org.apache.ibatis.annotations.Param;

/**
 * Created by MAC on 26/04/2017.
 */
public interface UserMapper {

    void insert(User user);

    User findUserByName(@Param("name") String name);

    User getUserByNameAndPassword(User user);
}
