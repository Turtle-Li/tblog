package com.turtle;

import com.turtle.dto.UserForgetEmailParam;
import com.turtle.dto.UserUpdateInfoParam;
import com.turtle.service.ApiService;
import com.turtle.service.LoginService;
import com.turtle.service.RoleService;
import com.turtle.service.UserService;
import com.turtle.util.RedisUtil;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;
import java.time.LocalDate;

@SpringBootTest
@Slf4j
class AdminApplicationTests {

    @Autowired
    private LoginService loginService;
    @Autowired
    private UserService userService;
    @Autowired
    private ApiService apiService;
    @Autowired
    private RoleService roleService;
    @Resource
    private RedisUtil redisUtil;
    @Test
    void send(){
        loginService.sendForgetEmail(new UserForgetEmailParam().setUserName("lijiayu").setEmail("282189765@qq.com"));
//        System.out.println(redisUtil.get("282189765@qq.com"+EmailConst.EMAIL_CODE));
//        loginService.checkName("lijiayu");
    }

    @Test
    void list(){
//        roleService.assign(new AssignRoleParam().setUserId(1259826206840233986L).setRoleIds(Set.of(1259827052512587778L)));
    }

    @Test
    void contextLoads(){
        userService.updateInfo(new UserUpdateInfoParam().setId(1259826206840233986L).setName("Turtle"));
    }

    public static void main(String[] args) {
        System.out.println(LocalDate.now().toString());
    }
}
