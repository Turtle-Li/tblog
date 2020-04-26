package com.turtle.admin.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.turtle.admin.constant.SqlConf;
import com.turtle.admin.constant.UserConst;
import com.turtle.admin.dto.RegisterDto;
import com.turtle.admin.entity.User;
import com.turtle.admin.mapper.LoginMapper;
import com.turtle.admin.service.LoginService;
import com.turtle.common.constant.EmailConst;
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
import org.springframework.util.IdGenerator;

import javax.annotation.Resource;
import javax.validation.constraints.NotBlank;

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

    private IdGenerator idGenerator;

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
        if(redisUtil.getExpire(email + EmailConst.EMAIL_CODE)>540){
            throw new UserAlertException("邮件发送间隔1分钟！");
        }
        rabbitSender.sendCodeEmil(email);
        return ResultBody.success();
    }

    @Override
    public ResultBody register(RegisterDto registerDto) {
        String userName = registerDto.getUserName();
        String passWord = registerDto.getPassWord();
        String email = registerDto.getEmail();
        String code = registerDto.getCode();

        if(!code.equals(redisUtil.get(email + EmailConst.EMAIL_CODE))){
            throw new UserAlertException("验证码不正确或已过期！");
        }
        QueryWrapper<User> wq = new QueryWrapper<User>().eq(SqlConf.USERNAME,userName);
        if(loginMapper.selectOne(wq) != null){
            throw new UserAlertException("账号已存在！");
        }
        if(loginMapper.selectOne(wq.eq(SqlConf.EMAIL,email)) != null){
            throw new UserAlertException("该邮箱已被使用！");
        }
        PasswordEncoder encoder = new BCryptPasswordEncoder();
        User user = new User()
                .setPassword(encoder.encode(passWord))
                .setAvatar(UserConst.DEFAULT_AVATAR)
                .setUserName(userName)
                .setEmail(email);
        loginMapper.insert(user);
        return ResultBody.success();
    }

}
