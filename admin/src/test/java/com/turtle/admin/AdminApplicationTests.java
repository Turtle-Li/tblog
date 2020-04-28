package com.turtle.admin;

import com.turtle.admin.dto.*;
import com.turtle.admin.entity.User;
import com.turtle.admin.mapper.UserMapper;
import com.turtle.admin.service.LoginService;
import com.turtle.admin.service.RoleService;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import javax.annotation.Resource;
import java.util.Set;

@SpringBootTest
@Slf4j
class AdminApplicationTests {

    @Autowired
    private LoginService loginService;
    @Resource
    private UserMapper userMapper;
    @Autowired
    private RoleService roleService;

    @Test
    void send(){
        loginService.sendCode("282189765@qq.com");
    }

    @Test
    void list(){
        PasswordEncoder encoder = new BCryptPasswordEncoder();
        log.info(encoder.encode("123456"));
    }

    @Test
    void contextLoads() {
        log.info(loginService.login(new LoginParam().setUserName("lijiayu").setPassword("123456").setIsRememberMe(0)).toString());
    }

}
