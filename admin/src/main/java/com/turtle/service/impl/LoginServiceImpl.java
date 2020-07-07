package com.turtle.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.qiniu.util.StringUtils;
import com.turtle.aop.RedissonLock;
import com.turtle.aop.ValidCode;
import com.turtle.constant.AlertExceptionConst;
import com.turtle.constant.EmailConst;
import com.turtle.constant.UserConst;
import com.turtle.dto.LoginParam;
import com.turtle.dto.RegisterParam;
import com.turtle.dto.UserChangePasswordParam;
import com.turtle.dto.UserForgetEmailParam;
import com.turtle.entity.sql.User;
import com.turtle.exception.UserAlertException;
import com.turtle.exception.UserPopupException;
import com.turtle.jwt.Audience;
import com.turtle.jwt.JwtHelper;
import com.turtle.mapper.UserMapper;
import com.turtle.send.RabbitSender;
import com.turtle.service.LoginService;
import com.turtle.service.RoleService;
import com.turtle.service.UserService;
import com.turtle.util.CheckUtils;
import com.turtle.util.RedisUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.util.DigestUtils;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.annotation.Resource;

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
    public void sendCode(String email) {
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
    }

    @Override
    @RedissonLock(spELString = "#param.userName")
    public void register(RegisterParam param) {
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
        User user = User
                .builder()
                .password(encoder.encode(password))
                .avatar(UserConst.DEFAULT_AVATAR)
                .userName(userName)
                .email(email)
                .build();
        userService.save(user);
    }


    @Override
    @ValidCode
    public String login(LoginParam param) {
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

        //todo 登录日志管理

        return StringUtils.isNullOrEmpty(user.getName())?user.getUserName():user.getName();
    }


    @Override
    public void sendForgetEmail(UserForgetEmailParam param) {
        User user = userService.getUserByUserName(param.getUserName());
        if(user==null){
            throw new UserAlertException(AlertExceptionConst.UNKNOW_USERNAME);
        }
        if(!param.getEmail().equals(user.getEmail())){
            throw new UserAlertException(AlertExceptionConst.INCORRECT_EMAIL);
        }
        rabbitSender.sendForgetUrl(param);
    }

    @Override
    public boolean validSid(String sid, String userName) {
        if(!redisUtil.hasKey(userName+EmailConst.EMAIL_FORGET)){
            throw new UserPopupException("链接已失效，请重新申请找回密码");
        }
        String uid = String.valueOf(redisUtil.get(userName+EmailConst.EMAIL_FORGET));
        if(!sid.equals(DigestUtils.md5DigestAsHex((userName + uid).getBytes()))){
            throw new UserPopupException("链接有误，请重新申请找回密码");
        }
        return true;
    }

    @Override
    public void changePassowrd(UserChangePasswordParam param) {
        String password = param.getPassword();
        String userName = param.getUserName();
        String sid = param.getSid();

        //校验sid
        this.validSid(sid,userName);

        User user = userService.getUserByUserName(userName);
        if(user==null){
            throw new UserAlertException(AlertExceptionConst.UNKNOW_USERNAME);
        }
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        user.setPassword(encoder.encode(password));
        userService.updateById(user);
    }
}
