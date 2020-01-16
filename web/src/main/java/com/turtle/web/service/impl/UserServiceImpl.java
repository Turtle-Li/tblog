package com.turtle.web.service.impl;

import com.turtle.web.entity.User;
import com.turtle.web.mapper.UserMapper;
import com.turtle.web.service.UserService;
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
