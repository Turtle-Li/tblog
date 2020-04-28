package com.turtle.admin.controller;

import com.turtle.admin.dto.LoginParam;
import com.turtle.admin.dto.RegisterParam;
import com.turtle.admin.service.LoginService;
import com.turtle.common.vo.ResultBody;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

/**
 * @author lijiayu
 * @date 2020/1/15
 * @description
 */
@RestController
@RequestMapping("/login")
public class LoginController {

    @Autowired
    private LoginService loginService;

    @GetMapping("/checkName")
    public boolean checkName(@RequestParam("username") String username){
        return loginService.checkName(username);
    }

    @GetMapping("/sendCode")
    public ResultBody sendCode(@RequestParam("email")String email){
        return loginService.sendCode(email);
    }

    @PostMapping("/register")
    public ResultBody register(@RequestBody @Valid RegisterParam param){
        return loginService.register(param);
    }

    @PostMapping
    public ResultBody login(@RequestBody @Valid LoginParam param){
        return loginService.login(param);
    }
}
