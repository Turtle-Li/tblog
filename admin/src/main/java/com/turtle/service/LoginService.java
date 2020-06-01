package com.turtle.service;


import com.baomidou.mybatisplus.extension.service.IService;
import com.turtle.dto.LoginParam;
import com.turtle.dto.RegisterParam;
import com.turtle.dto.UserChangePasswordParam;
import com.turtle.dto.UserForgetEmailParam;
import com.turtle.entity.sql.User;
import com.turtle.vo.ResultBody;

import javax.servlet.http.HttpServletResponse;

/**
 * @author lijiayu
 * @date 2020/1/15
 * @description
 */
public interface LoginService extends IService<User> {
    /**
     * 校验用户名
     * @param userName
     * @return
     */
    boolean checkName(String userName);

    /**
     * 发送验证码
     * @param email
     * @return
     */
    void sendCode(String email);

    /**
     * 注册
     * @param param
     * @return
     */
    void register(RegisterParam param);

    /**
     * 登录
     * @param param
     * @return
     */
    String login(LoginParam param);

    void sendForgetEmail(UserForgetEmailParam param);

    boolean validSid(String sid,String userName);

    void changePassowrd(UserChangePasswordParam param);
}
