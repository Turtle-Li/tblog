package com.turtle.controller;

import com.turtle.dto.LoginParam;
import com.turtle.dto.RegisterParam;
import com.turtle.service.LoginService;
import com.turtle.vo.ResultBody;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

/**
 * @author lijiayu
 * @date 2020/1/15
 * @description
 */
@RestController
@RequestMapping("/login")
@Api(value = "login" , tags = {"登录功能"})
public class LoginController {

    @Autowired
    private LoginService loginService;

    @GetMapping("/check-name")
    @ApiOperation(value = "检验用户名是否重复",notes = "需要用户传userName")
    @ApiImplicitParam(name = "userName" , value = "用户名",required = true)
    public boolean checkName(@RequestParam("userName") String userName){
        return loginService.checkName(userName);
    }

    @GetMapping("/send-code")
    @ApiOperation(value = "发送验证码",notes = "发送验证码")
    @ApiImplicitParam(value = "邮箱" ,required = true)
    public ResultBody sendCode(@RequestParam("email")String email){
        return loginService.sendCode(email);
    }

    @PostMapping("/register")
    @ApiOperation(value = "注册",notes = "注册接口")
    public ResultBody register(@RequestBody @Valid RegisterParam param){
        return loginService.register(param);
    }

    @PostMapping
    @ApiOperation(value = "登录",notes = "登录接口")
    public ResultBody login(@RequestBody @Valid LoginParam param){
        return loginService.login(param);
    }
}
