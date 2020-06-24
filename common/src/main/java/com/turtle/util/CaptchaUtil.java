package com.turtle.util;

import com.dingxianginc.ctu.client.CaptchaClient;
import com.dingxianginc.ctu.client.model.CaptchaResponse;
import com.turtle.exception.FrontRequestException;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;

/**
 * @author lijiayu
 * @date 2020/5/15
 * @description
 */
@Component
@Slf4j
public class CaptchaUtil {

    @Value("${appId}")
    private String appId;

    @Value("${appSecret}")
    private String appSecret;

    @Value("${appHeader}")
    private String appHeader;

    public boolean validCodeToken() {
        //获取ip地址
        ServletRequestAttributes requestAttributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = requestAttributes.getRequest();
        String ip = request.getRemoteAddr();
        String token = request.getHeader(appHeader);
        if(StringUtils.isBlank(token)){
            throw new FrontRequestException("CodeToken为空！");
        }
        CaptchaClient captchaClient = new CaptchaClient(appId, appSecret);
        //captchaClient.setCaptchaUrl(captchaUrl);
        //特殊情况需要额外指定服务器,可以在这个指定，默认情况下不需要设置
        CaptchaResponse response = null;
        try {
            response = captchaClient.verifyToken(token, ip);
        } catch (Exception e) {
            log.error("校验验证码服务器出错！");
            throw new FrontRequestException("校验验证码服务器出错！");
        }
        //针对一些token冒用的情况，采集客户端ip随token一起提交到验证码服务，验证码服务除了判断token的合法性还会校验提交业务参数的客户端ip和验证码颁发token的客户端ip是否一致
        log.info(response.getCaptchaStatus());
        //确保验证状态是SERVER_SUCCESS，SDK中有容错机制，在网络出现异常的情况会返回通过
        log.info(response.getIp());
        //验证码服务采集到的客户端ip
        /**token验证通过，继续其他流程**/
        /**token验证失败，业务系统可以直接阻断该次请求或者继续弹验证码**/
        return response.getResult();
    }
}
