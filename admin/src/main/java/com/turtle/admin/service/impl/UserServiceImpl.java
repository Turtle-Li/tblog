package com.turtle.admin.service.impl;

import com.turtle.admin.entity.User;
import com.turtle.admin.mapper.UserMapper;
import com.turtle.admin.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author lijiayu
 * @date 2020/1/15
 * @description
 */
@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserMapper userMapper;

    @Override
    public List<User> getUserList() {
        return userMapper.selectList(null);
    }
}
