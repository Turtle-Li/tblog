package com.turtle.config.security;

import com.turtle.vo.ResultBody;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author lijiayu
 * @date 2020/1/20
 * @description
 */
@Component
@Slf4j
public class MyAccessDeniedHandler implements AccessDeniedHandler {
    @Override
    public void handle(HttpServletRequest httpServletRequest,
                       HttpServletResponse httpServletResponse,
                       AccessDeniedException e) throws IOException {
        httpServletResponse.setCharacterEncoding("UTF-8");
        httpServletResponse.setContentType("application/json; charset=utf-8");
        httpServletResponse.getWriter().print(ResultBody.error(String.valueOf(HttpServletResponse.SC_PROXY_AUTHENTICATION_REQUIRED), "该用户无权限！"));
        log.info("该用户无权限！");
    }
}
