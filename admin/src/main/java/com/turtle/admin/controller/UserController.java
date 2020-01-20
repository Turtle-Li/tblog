package com.turtle.admin.controller;

import com.turtle.admin.entity.User;
import com.turtle.admin.service.UserService;
import com.turtle.common.exception.BizException;
import com.turtle.common.exception.FrontRequestException;
import com.turtle.common.send.RabbitSender;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author lijiayu
 * @date 2020/1/15
 * @description
 */
@RestController
@RequestMapping("/pub")
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping("/test")
    public List<User> getUser(Integer type){
        return userService.getUserList();
    }
}
