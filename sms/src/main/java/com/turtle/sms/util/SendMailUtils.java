package com.turtle.sms.util;

import com.turtle.common.util.RedisUtil;
import com.turtle.sms.constant.emailConst;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import javax.annotation.Resource;
import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

/**
 * @author lijiayu
 * @date 2020/1/18
 * @description
 */
@Component
public class SendMailUtils {

    @Value(value = "${spring.mail.username}")
    public String SENDER;
    @Autowired
    private JavaMailSenderImpl mailSender;

    @Resource
    private RedisUtil redisUtil;

    /**
     * 发送邮件
     *
     * @param receiver
     */
    public void sendCodeEmail(String receiver) throws MessagingException {
        //创建一个复杂的消息邮件
        MimeMessage mimeMessage = mailSender.createMimeMessage();

        MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, true);//multipart:true

        helper.setSubject("turtle博客网站验证邮件");
        int code = (int) (Math.random() * 1000000);
        helper.setText(String.format(emailConst.EMAIL_TEXT, code), true);
        helper.setTo(receiver);//邮件接收人
        helper.setFrom(SENDER);//邮件发送者

        mailSender.send(mimeMessage);
        //验证码存缓存
        redisUtil.set(receiver,code,600);
    }
}
