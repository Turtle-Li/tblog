package com.turtle.sms.listener;

import com.turtle.sms.util.SendMailUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.mail.MessagingException;
import java.util.Map;

/**
 * @author lijiayu
 * @date 2020/1/18
 * @description
 */
@Component
@Slf4j
public class SendListener {

    @Autowired
    private SendMailUtils sendMailUtils;

    @RabbitHandler
    @RabbitListener(queues = "emailDirect")
    public void sendEmil(Map<String,String> sendMsg){
        try {
            sendMailUtils.sendCodeEmail(sendMsg.get("receiver"));
        } catch (MessagingException e) {
            e.printStackTrace();
        }
        log.info("email: {} send success", sendMsg.get("receiver"));
    }
}
