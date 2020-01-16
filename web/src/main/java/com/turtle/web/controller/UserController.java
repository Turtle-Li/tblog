package com.turtle.web.controller;

import com.turtle.common.aop.TargetDataSource;
import com.turtle.common.enums.ExceptionEnum;
import com.turtle.common.exception.BizException;
import com.turtle.common.exception.FrontRequestException;
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

    @GetMapping("/test")
    public List<User> getUser(Integer type){
        if(type==null){
            throw new NullPointerException();
        }else if(type==1){
            throw new BizException("-1","自定义异常");
        }else if(type==2){
            throw new FrontRequestException("前端异常");
        }
        return userService.getUserList();
    }
}
