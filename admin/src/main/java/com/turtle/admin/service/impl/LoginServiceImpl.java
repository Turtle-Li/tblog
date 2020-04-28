package com.turtle.admin.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.turtle.admin.constant.SqlConf;
import com.turtle.admin.constant.UserConst;
import com.turtle.admin.dto.LoginParam;
import com.turtle.admin.dto.RegisterParam;
import com.turtle.admin.entity.Role;
import com.turtle.admin.entity.User;
import com.turtle.admin.mapper.UserMapper;
import com.turtle.admin.service.LoginService;
import com.turtle.admin.service.RoleService;
import com.turtle.common.constant.EmailConst;
import com.turtle.common.enums.ExceptionEnum;
import com.turtle.common.exception.UserAlertException;
import com.turtle.common.jwt.Audience;
import com.turtle.common.jwt.JwtHelper;
import com.turtle.common.send.RabbitSender;
import com.turtle.common.util.CheckUtils;
import com.turtle.common.util.RedisUtil;
import com.turtle.common.vo.ResultBody;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.util.IdGenerator;

import javax.annotation.Resource;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author lijiayu
 * @date 2020/1/15
 * @description
 */
@Service
@Slf4j
public class LoginServiceImpl extends ServiceImpl<UserMapper,User> implements LoginService {

    @Autowired
    private UserMapper userMapper;
    @Autowired
    private RoleService roleService;

    @Autowired
    private RabbitSender rabbitSender;
    @Autowired
    private JwtHelper jwtHelper;
    @Resource
    private RedisUtil redisUtil;
    @Autowired
    private Audience audience;

    @Value("${tokenHead}")
    private String tokenHead;

    @Value("${rememberMeExpiresSecond}")
    private int rememberMeExpiresSecond;

    @Override
    public boolean checkName(String userName) {
        QueryWrapper<User> wrapper = new QueryWrapper<>();
        wrapper.eq(SqlConf.USERNAME,userName);
        return userMapper.selectOne(wrapper) == null;
    }

    @Override
    public ResultBody sendCode(String email) {
        if(!CheckUtils.checkEmail(email)){
            throw new UserAlertException("邮箱格式不正确！");
        }
        QueryWrapper<User> wrapper = new QueryWrapper<>();
        wrapper.eq("email",email);
        if(userMapper.selectOne(wrapper)!=null){
            throw new UserAlertException("该邮箱已被注册！");
        }
        if(redisUtil.getExpire(email + EmailConst.EMAIL_CODE)>540){
            throw new UserAlertException("邮件发送间隔1分钟！");
        }
        rabbitSender.sendCodeEmil(email);
        return ResultBody.success();
    }

    @Override
    public ResultBody register(RegisterParam param) {
        String userName = param.getUserName();
        String passWord = param.getPassWord();
        String email = param.getEmail();
        String code = param.getCode();

        if(!code.equals(redisUtil.get(email + EmailConst.EMAIL_CODE))){
            throw new UserAlertException("验证码不正确或已过期！");
        }
        QueryWrapper<User> wq = new QueryWrapper<User>().eq(SqlConf.USERNAME,userName);
        if(userMapper.selectOne(wq) != null){
            throw new UserAlertException("账号已存在！");
        }
        if(userMapper.selectOne(wq.eq(SqlConf.EMAIL,email)) != null){
            throw new UserAlertException("该邮箱已被使用！");
        }
        PasswordEncoder encoder = new BCryptPasswordEncoder();
        User user = new User()
                .setPassword(encoder.encode(passWord))
                .setAvatar(UserConst.DEFAULT_AVATAR)
                .setUserName(userName)
                .setEmail(email);
        userMapper.insert(user);
        return ResultBody.success();
    }


    @Override
    public ResultBody login(LoginParam param) {
        QueryWrapper<User> qw = new QueryWrapper<User>().eq(SqlConf.USERNAME, param.getUserName());
        User user = userMapper.selectOne(qw);
        if(user==null){
            throw new UserAlertException("用户名不存在！");
        }
        //验证密码
        PasswordEncoder encoder = new BCryptPasswordEncoder();
        boolean matches = encoder.matches(param.getPassword(), user.getPassword());
        if(!matches){
            throw new UserAlertException("登录失败，用户名或密码错误！");
        }

//        List<Role> roleList = roleService.getMyRoleList(user.getId());
//        String roleName = roleList.stream().map(Role::getName).collect(Collectors.joining(","));

        int expiresSecond = param.getIsRememberMe()==1?rememberMeExpiresSecond:audience.getExpiresSecond();

        String jwt = jwtHelper.createJWT(user.getUserName(), user.getId(), audience.getClientId(), audience.getName(), expiresSecond * 1000, audience.getBase64Secret());
        String token = tokenHead+jwt;

        return ResultBody.result(ExceptionEnum.SUCCESS.getResultCode(),ExceptionEnum.SUCCESS.getResultMsg(),token);
    }
}
