package com.turtle.aop.aspect;

import com.turtle.aop.ValidCode;
import com.turtle.exception.UserAlertException;
import com.turtle.util.CaptchaUtil;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @author lijiayu
 * @date 2020/5/19
 * @description
 */
@Aspect
@Component
@Slf4j
public class ValidCodeAspect {

    @Autowired
    private CaptchaUtil captchaUtil;

    @Before("@annotation(validCode)")
    public void before(JoinPoint joinPoint, ValidCode validCode){
        boolean flag = captchaUtil.validCodeToken();
        if(!flag){
            throw new UserAlertException("验证码校验失败，请重试！");
        }
    }
}
