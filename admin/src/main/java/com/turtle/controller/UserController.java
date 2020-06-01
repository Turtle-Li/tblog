package com.turtle.controller;

import com.turtle.dto.UserUpdateInfoParam;
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

    @PutMapping("/update-info")
    @ApiOperation(value = "修改信息",notes = "修改用户基本信息")
    public ResultBody updateInfo(@RequestBody @Valid UserUpdateInfoParam userUpdateInfoParam){
        userService.updateInfo(userUpdateInfoParam);
        return ResultBody.success();
    }

    @PostMapping("/token-user")
    @ApiOperation(value = "获取用户信息",notes = "通过token获取用户基本信息")
    public ResultBody getByTokenInfo(@RequestAttribute("token")  String token){
        return userService.getByTokenInfo(token);
    }
}