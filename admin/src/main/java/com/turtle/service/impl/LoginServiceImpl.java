package com.turtle.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.qiniu.util.StringUtils;
import com.turtle.aop.RedissonLock;
import com.turtle.aop.ValidCode;
import com.turtle.constant.AlertExceptionConst;
import com.turtle.constant.SqlConf;
import com.turtle.constant.UserConst;
import com.turtle.dto.LoginParam;
import com.turtle.dto.RegisterParam;
import com.turtle.dto.UserForgetEmailParam;
import com.turtle.entity.sql.User;
import com.turtle.mapper.UserMapper;
import com.turtle.service.LoginService;
import com.turtle.service.RoleService;
import com.turtle.constant.EmailConst;
import com.turtle.enums.ExceptionEnum;
import com.turtle.exception.UserAlertException;
import com.turtle.jwt.Audience;
import com.turtle.jwt.JwtHelper;
import com.turtle.send.RabbitSender;
import com.turtle.service.UserService;
import com.turtle.util.CheckUtils;
import com.turtle.util.RedisUtil;
import com.turtle.vo.ResultBody;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;

/**
 * @author lijiayu
 * @date 2020/1/15
 * @description
 */
@Service
@Slf4j
public class LoginServiceImpl extends ServiceImpl<UserMapper,User> implements LoginService {

    @Autowired
    private UserService userService;
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
    @Value("${tokenHeader}")
    private String tokenHeader;

    @Value("${rememberMeExpiresSecond}")
    private int rememberMeExpiresSecond;

    @Override
    public boolean checkName(String userName) {
        return userService.getUserByUserName(userName) == null;
    }

    @Override
    public ResultBody sendCode(String email) {
        if(!CheckUtils.checkEmail(email)){
            throw new UserAlertException("邮箱格式不正确！");
        }
        if(userService.getUserByEmail(email)!=null){
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
        String password = param.getPassword();
        String email = param.getEmail();
        String code = param.getCode();

//        if(!code.equals(redisUtil.get(email + EmailConst.EMAIL_CODE))){
//            throw new UserAlertException("验证码不正确或已过期！");
//        }
        if(userService.getUserByUserName(userName) != null){
            throw new UserAlertException(AlertExceptionConst.USERNAME_ALREADY_EXISTS);
        }
        if(userService.getUserByEmail(email) != null){
            throw new UserAlertException(AlertExceptionConst.EMAIL_ALREADY_USED);
        }
        PasswordEncoder encoder = new BCryptPasswordEncoder();
        User user = new User()
                .setPassword(encoder.encode(password))
                .setAvatar(UserConst.DEFAULT_AVATAR)
                .setUserName(userName)
                .setEmail(email);
        userService.save(user);
        return ResultBody.success();
    }


    @Override
    @ValidCode
    public ResultBody login(LoginParam param) {
        User user = userService.getUserByUserName(param.getUserName());
        if(user==null){
            throw new UserAlertException(AlertExceptionConst.UNKNOW_USERNAME);
        }
        //验证密码
        PasswordEncoder encoder = new BCryptPasswordEncoder();
        boolean matches = encoder.matches(param.getPassword(), user.getPassword());
        if(!matches){
            throw new UserAlertException(AlertExceptionConst.INCORRECT_NAME_PASSWORD);
        }

        int expiresSecond = param.getIsRememberMe()==1?rememberMeExpiresSecond:audience.getExpiresSecond();

        String jwt = jwtHelper.createJWT(user.getUserName(), user.getId(), audience.getClientId(), audience.getName(), expiresSecond * 1000, audience.getBase64Secret());
        String token = tokenHead+jwt;
        ServletRequestAttributes requestAttributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        requestAttributes.getResponse().setHeader(tokenHeader,token);
        return ResultBody.result(ExceptionEnum.SUCCESS.getResultCode(),ExceptionEnum.SUCCESS.getResultMsg(), StringUtils.isNullOrEmpty(user.getName())?user.getUserName():user.getName());
    }


    @Override
    public ResultBody sendForgetEmail(UserForgetEmailParam param) {
        User user = userService.getUserByUserName(param.getUserName());
        if(user==null){
            throw new UserAlertException(AlertExceptionConst.UNKNOW_USERNAME);
        }
        if(!param.getEmail().equals(user.getEmail())){
            throw new UserAlertException(AlertExceptionConst.INCORRECT_EMAIL);
        }
        rabbitSender.sendForgetUrl(param);
        return ResultBody.success();
    }
}
