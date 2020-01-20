package com.turtle.admin.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.turtle.admin.entity.User;
import com.turtle.admin.mapper.LoginMapper;
import com.turtle.admin.service.LoginService;
import com.turtle.common.exception.UserAlertException;
import com.turtle.common.send.RabbitSender;
import com.turtle.common.util.RedisUtil;
import com.turtle.common.vo.ResultBody;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * @author lijiayu
 * @date 2020/1/15
 * @description
 */
@Service
public class LoginServiceImpl implements LoginService {

    @Autowired
    private LoginMapper loginMapper;
    @Autowired
    private RabbitSender rabbitSender;
    @Resource
    private RedisUtil redisUtil;

    @Override
    public boolean checkName(String username) {
        QueryWrapper<User> wrapper = new QueryWrapper<>();
        wrapper.eq("username",username).eq("is_delete",1);
        return loginMapper.selectOne(wrapper) == null;
    }

    @Override
    public ResultBody sendCode(String email) {
        QueryWrapper<User> wrapper = new QueryWrapper<>();
        wrapper.eq("email",email).eq("is_delete",1);
        if(loginMapper.selectOne(wrapper)!=null){
            throw new UserAlertException("该邮箱已被注册！");
        }
        if(redisUtil.getExpire(email+"code")>540){
            throw new UserAlertException("邮件发送间隔1分钟！");
        }
        rabbitSender.sendCodeEmil(email);
        return ResultBody.success();
    }
}
