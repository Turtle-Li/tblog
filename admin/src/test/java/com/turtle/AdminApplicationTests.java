package com.turtle;

import com.turtle.constant.EmailConst;
import com.turtle.constant.UserConst;
import com.turtle.dto.*;
import com.turtle.entity.sql.User;
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
import java.time.LocalDateTime;
import java.util.Set;
import java.util.concurrent.CompletableFuture;

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
    void contextLoads() throws InterruptedException {
        PageEntity<User> list = userService.getList(new UserListParam().setName("li").setStatus(0).setEndTime(LocalDateTime.now()));
        System.out.println(list.getData());
        userService.updateStatus(1265585436445605889L, UserConst.STATUS_FREEZE);
    }
}
