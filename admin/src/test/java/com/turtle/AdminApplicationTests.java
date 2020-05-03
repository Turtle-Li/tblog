package com.turtle;

import com.turtle.dto.LoginParam;
import com.turtle.mapper.UserMapper;
import com.turtle.service.ApiService;
import com.turtle.service.LoginService;
import com.turtle.service.RoleService;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import javax.annotation.Resource;
import java.io.File;

@SpringBootTest
@Slf4j
class AdminApplicationTests {

    @Autowired
    private LoginService loginService;
    @Autowired
    private ApiService apiService;
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
