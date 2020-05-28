package com.turtle.service;


import com.baomidou.mybatisplus.extension.service.IService;
import com.turtle.dto.LoginParam;
import com.turtle.dto.RegisterParam;
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
    ResultBody sendCode(String email);

    /**
     * 注册
     * @param param
     * @return
     */
    ResultBody register(RegisterParam param);

    /**
     * 登录
     * @param param
     * @return
     */
    ResultBody login(LoginParam param);

    ResultBody test(User user,String a) throws InterruptedException;
}
