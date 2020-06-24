package com.turtle.controller;

import com.turtle.dto.PageEntity;
import com.turtle.dto.UserDto;
import com.turtle.dto.UserListParam;
import com.turtle.dto.UserUpdateInfoParam;
import com.turtle.entity.sql.User;
import com.turtle.service.UserService;
import com.turtle.vo.ResultBody;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

/**
 * @author lijiayu
 * @date 2020/6/1
 * @description
 */
@RestController
@RequestMapping("/admin")
@PreAuthorize("hasRole('admin')")
@Api(tags = "管理员相关接口")
public class AdminController {
    @Autowired
    private UserService userService;

    @GetMapping
    @ApiOperation("获取用户信息")
    public ResultBody get(@RequestParam("id") Long id){
        UserDto userDto = userService.get(id);
        return ResultBody.result(userDto);
    }

    @PostMapping("/list")
    @ApiOperation("获取用户列表")
    public ResultBody getList(UserListParam param){
        PageEntity<User> pageEntity = userService.getList(param);
        return ResultBody.result(pageEntity);
    }

    @PutMapping("/status")
    public ResultBody updateStatus(@RequestParam("id") Long id,@RequestParam("status") Integer status){
        userService.updateStatus(id,status);
        return ResultBody.success();
    }



    @PutMapping
    @ApiOperation(value = "修改信息",notes = "修改用户基本信息")
    public ResultBody update(@RequestBody @Valid UserUpdateInfoParam userUpdateInfoParam){
        userService.updateInfo(userUpdateInfoParam);
        return ResultBody.success();
    }

    @PostMapping("/token-user")
    @ApiOperation(value = "获取用户信息",notes = "通过token获取用户基本信息")
    public ResultBody getByTokenInfo(@RequestAttribute("token")  String token){
        return userService.getByTokenInfo(token);
    }
}
