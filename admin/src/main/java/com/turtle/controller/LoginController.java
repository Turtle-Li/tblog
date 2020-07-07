package com.turtle.controller;

import com.turtle.dto.LoginParam;
import com.turtle.dto.RegisterParam;
import com.turtle.dto.UserChangePasswordParam;
import com.turtle.dto.UserForgetEmailParam;
import com.turtle.service.LoginService;
import com.turtle.vo.ResultBody;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.Email;

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
    public ResultBody checkName(@RequestParam("userName") String userName){
        boolean flag = loginService.checkName(userName);
        return ResultBody.result(flag);
    }

    @GetMapping("/send-code")
    @ApiOperation(value = "发送验证码",notes = "发送验证码")
    @ApiImplicitParam(value = "邮箱" ,required = true)
    public ResultBody sendCode(@RequestParam("email") @Email String email){
        loginService.sendCode(email);
        return ResultBody.success();
    }

    @PostMapping("/register")
    @ApiOperation(value = "注册",notes = "注册接口")
    public ResultBody register(@RequestBody @Valid RegisterParam param){
        loginService.register(param);
        return ResultBody.success();
    }

    @PostMapping
    @ApiOperation(value = "登录",notes = "登录接口")
    public ResultBody login(@RequestBody @Valid LoginParam param){
        String name = loginService.login(param);
        return ResultBody.result(name);
    }

    @PostMapping("/send-forget-email")
    @ApiOperation("忘记密码发送邮件")
    public ResultBody sendForgetEmail(@RequestBody @Valid UserForgetEmailParam param){
        loginService.sendForgetEmail(param);
        return ResultBody.success();
    }

    @GetMapping("/valid-sid")
    @ApiOperation("验证修改密码链接")
    public ResultBody validSid(@RequestParam("sid") String sid, @RequestParam("userName") String userName) {
        boolean flag = loginService.validSid(sid, userName);
        return ResultBody.result(flag);
    }

    @PostMapping("/change-password")
    @ApiOperation("修改密码")
    public ResultBody changePassword(@RequestBody @Valid UserChangePasswordParam param){
        loginService.changePassowrd(param);
        return ResultBody.success();
    }

}
