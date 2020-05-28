package com.turtle.util;

import com.turtle.constant.EmailConst;
import com.turtle.constant.EmailText;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.util.Random;

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
     * @param email
     */
    public void sendCodeEmail(String email) throws MessagingException {
        //创建一个复杂的消息邮件
        MimeMessage mimeMessage = mailSender.createMimeMessage();

        MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, true);

        helper.setSubject("turtle验证");
        String code = String.format("%06d",new Random().nextInt(1000000));
        helper.setText(String.format(EmailText.CODE_TEXT, code), true);
        //邮件接收人
        helper.setTo(email);
        //邮件发送者
        helper.setFrom(SENDER);

        mailSender.send(mimeMessage);
        //验证码存缓存
        redisUtil.set(email + EmailConst.EMAIL_CODE,code,600);
    }
}
