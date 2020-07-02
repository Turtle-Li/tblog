package com.turtle.util;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;

/**
 * @author lijiayu
 * @date 2020/7/2
 * @description
 */
@Slf4j
public class IpUtil {

    /**
     * 获取访问ip
     * @return
     */
    public static String getIp(){
        ServletRequestAttributes requestAttributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        if(requestAttributes==null){
            return "127.0.0.1";
        }
        HttpServletRequest request = requestAttributes.getRequest();
        return request.getRemoteAddr();
    }
}
