package com.turtle.controller;

import com.turtle.dto.UserDto;
import com.turtle.dto.UserUpdateInfoParam;
import com.turtle.entity.sql.User;
import com.turtle.service.UserService;
import com.turtle.vo.ResultBody;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

/**
 * @Auther: fuzongle
 * @Date: 2020/5/10 19:08
 * @Description: 用户相关功能API
 */
@RestController
@RequestMapping("/user")
@Api(tags = {"用户相关功能"})
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping
    public ResultBody get(@RequestAttribute Long userId){
        UserDto userDto = userService.get(userId);
        return ResultBody.result(userDto);
    }

    @PutMapping
    public ResultBody update(@RequestBody UserUpdateInfoParam param,
                             @RequestAttribute Long userId){
        userService.updateInfo(param.setId(userId));
        return ResultBody.success();
    }

    

}