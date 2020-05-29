package com.turtle.util;

import com.turtle.constant.EmailConst;
import com.turtle.constant.EmailText;
import com.turtle.dto.UserForgetEmailParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Component;
import org.springframework.util.DigestUtils;

import javax.annotation.Resource;
import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.util.Random;
import java.util.UUID;

/**
 * @author lijiayu
 * @date 2020/1/18
 * @description
 */
@Component
public class SendMailUtils {

    @Value(value = "${spring.mail.userName}")
    public String SENDER;
    @Autowired
    private JavaMailSenderImpl mailSender;

    @Resource
    private RedisUtil redisUtil;

    /**
     * 发送邮件
     *
     * @param email
     */
    public void sendCodeEmail(String email) throws MessagingException {
        //创建一个复杂的消息邮件
        MimeMessage mimeMessage = mailSender.createMimeMessage();

        MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, true);

        helper.setSubject("Turtle验证");
        String code = String.format("%06d",new Random().nextInt(1000000));
        helper.setText(String.format(EmailText.CODE_TEXT, code), true);
        //邮件接收人
        helper.setTo(email);
        //邮件发送者
        helper.setFrom(SENDER);

        mailSender.send(mimeMessage);
        //验证码缓存
        redisUtil.set(email + EmailConst.EMAIL_CODE,code,600);
    }


    public void sendForgetEmail(UserForgetEmailParam param) throws MessagingException {
        String userName = param.getUserName();
        String email = param.getEmail();
        //创建一个复杂的消息邮件
        MimeMessage mimeMessage = mailSender.createMimeMessage();

        MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, true);

        helper.setSubject("Turtle忘记密码验证");

        String uid = UUID.randomUUID().toString();
        String sid = DigestUtils.md5DigestAsHex((userName+uid).getBytes());
        String url = "http://www.lijiayu.vip:8080/admin/v1/login/to-change-password?sid="+sid+"&userName="+userName;

        helper.setText(String.format(EmailText.FORGET_TEXT, url), true);
        //邮件接收人
        helper.setTo(email);
        //邮件发送者
        helper.setFrom(SENDER);

        mailSender.send(mimeMessage);
        //url缓存
        redisUtil.set(userName + EmailConst.EMAIL_FORGET,uid,6000);
    }
}
