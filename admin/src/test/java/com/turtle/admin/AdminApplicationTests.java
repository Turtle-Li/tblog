package com.turtle.admin;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.turtle.admin.constant.UserConst;
import com.turtle.admin.dto.RegisterDto;
import com.turtle.admin.entity.User;
import com.turtle.admin.mapper.LoginMapper;
import com.turtle.admin.service.LoginService;
import com.turtle.common.util.RedisUtil;
import com.turtle.common.vo.ResultBody;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.keyvalue.core.IdentifierGenerator;

import javax.annotation.Resource;
import java.util.List;
import java.util.Random;

@SpringBootTest
@Slf4j
class AdminApplicationTests {

    @Autowired
    private LoginService loginService;
    @Resource
    private LoginMapper loginMapper;

    @Test
    void send(){
        loginService.sendCode("282189765@qq.com");
    }

    @Test
    void contextLoads() {
        QueryWrapper<User> qw = new QueryWrapper<>();
        Page page = new Page(1,2);
        IPage ipage = loginMapper.selectPage(page,qw);
        log.info(ipage.getRecords().toString());
    }

}
