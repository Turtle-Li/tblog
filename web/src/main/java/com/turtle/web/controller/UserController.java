package com.turtle.web.controller;

import com.turtle.common.aop.TargetDataSource;
import com.turtle.common.enums.ExceptionEnum;
import com.turtle.common.exception.BizException;
import com.turtle.common.exception.FrontRequestException;
import com.turtle.common.util.RedisUtil;
import com.turtle.web.entity.User;
import com.turtle.web.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author lijiayu
 * @date 2020/1/15
 * @description
 */
@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private RedisUtil redisUtil;

    @GetMapping("/test")
    public List<User> getUser(Integer type){
        redisUtil.set("test","123");
        return userService.getUserList();
    }
}
