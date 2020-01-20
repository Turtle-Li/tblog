package com.turtle.admin.controller;

import com.turtle.admin.dto.RegisterDto;
import com.turtle.admin.service.LoginService;
import com.turtle.common.vo.ResultBody;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

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
    public ResultBody register(@RequestBody RegisterDto registerDto){
        return loginService.registrer(registerDto);
    }
}
