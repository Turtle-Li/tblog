package com.turtle.web.controller;

import com.turtle.common.aop.TargetDataSource;
import com.turtle.common.enums.ExceptionEnum;
import com.turtle.common.exception.BizException;
import com.turtle.common.exception.FrontRequestException;
import com.turtle.common.send.RabbitSender;
import com.turtle.common.util.RedisUtil;
import com.turtle.web.entity.User;
import com.turtle.web.service.UserService;
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
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private RabbitSender rabbitSender;

    @GetMapping("/test")
    public List<User> getUser(Integer type){
        Map<String,String> map = new HashMap<>();
        map.put("receiver","282189765@qq.com");
        map.put("text","test");
        rabbitSender.sendEmil(map);
        return userService.getUserList();
    }
}
