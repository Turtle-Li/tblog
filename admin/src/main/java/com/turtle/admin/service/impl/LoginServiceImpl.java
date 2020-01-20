package com.turtle.admin.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.turtle.admin.dto.RegisterDto;
import com.turtle.admin.entity.User;
import com.turtle.admin.mapper.LoginMapper;
import com.turtle.admin.service.LoginService;
import com.turtle.common.exception.UserAlertException;
import com.turtle.common.send.RabbitSender;
import com.turtle.common.util.CheckUtils;
import com.turtle.common.util.RedisUtil;
import com.turtle.common.vo.ResultBody;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * @author lijiayu
 * @date 2020/1/15
 * @description
 */
@Service
@Slf4j
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
        if(!CheckUtils.checkEmail(email)){
            throw new UserAlertException("邮箱格式不正确！");
        }
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

    @Override
    public ResultBody register(RegisterDto registerDto) {
        String username = registerDto.getUsername();
        String password = registerDto.getPassword();
        String email = registerDto.getEmail();

        if(!checkRegister(username,password,email,registerDto.getCode())){
            throw new UserAlertException("传入参数有误，注册失败！");
        }

        PasswordEncoder encoder = new BCryptPasswordEncoder();

        User user = new User();

        return null;
    }

    /**
     * @author lijiayu
     * @date 2020/1/20
     * @param username
     * @param password
     * @param email
     * @return boolean
     * @description 注册校验
     */
    private boolean checkRegister(String username,String password,String email,int code){
        if(StringUtils.isBlank(username)){
            throw new UserAlertException("用户名不能为空！");
        }
        if(StringUtils.isBlank(password)){
            throw new UserAlertException("密码不能为空！");
        }
        if(!CheckUtils.checkPassword(password)){
            throw new UserAlertException("密码格式不正确！");
        }
        if(StringUtils.isBlank(email)){
            throw new UserAlertException("邮箱不能为空！");
        }
        if(!CheckUtils.checkEmail(email)){
            throw new UserAlertException("邮箱格式不正确！");
        }
        if(code!=(int)redisUtil.get(email+"code")){
            throw new UserAlertException("验证码不正确！");
        }
        return true;
    }
}
