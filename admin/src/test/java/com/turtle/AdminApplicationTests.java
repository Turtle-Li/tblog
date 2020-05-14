package com.turtle;

import com.turtle.constant.EmailConst;
import com.turtle.dto.*;
import com.turtle.entity.sql.User;
import com.turtle.mapper.UserMapper;
import com.turtle.service.ApiService;
import com.turtle.service.LoginService;
import com.turtle.service.RoleService;
import com.turtle.util.RedisUtil;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import javax.annotation.Resource;
import java.io.File;
import java.util.Set;

@SpringBootTest
@Slf4j
class AdminApplicationTests {

    @Autowired
    private LoginService loginService;
    @Autowired
    private ApiService apiService;
    @Autowired
    private RoleService roleService;
    @Resource
    private RedisUtil redisUtil;
    @Test
    void send(){
        loginService.sendCode("282189765@qq.com");
    }

    @Test
    void list(){
        roleService.assign(new AssignRoleParam().setUserId(1259826206840233986L).setRoleIds(Set.of(1259827052512587778L)));
    }

    @Test
    void contextLoads() throws InterruptedException {
        User user = new User();
        user.setId(1259826206840233986L);
        for (int i = 0; i < 10; i++) {
            new Thread(() -> {
                try {
                    loginService.test(user,"888888");
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            ).start();
        }
        Thread.sleep(60000);
//        loginService.test();
    }

}
