package com.turtle.config.security;

import com.turtle.constant.AlertExceptionConst;
import com.turtle.jwt.Audience;
import com.turtle.jwt.JwtHelper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component
@Slf4j
public class JwtAuthenticationTokenFilter extends OncePerRequestFilter {

    @Autowired
    private Audience audience;

    @Autowired
    private SecurityUserDetailsService userDetailsService;

    @Autowired
    private JwtHelper jwtHelper;

    @Value(value = "${tokenHead}")
    private String tokenHead;

    @Value(value = "${tokenHeader}")
    private String tokenHeader;

    @Value(value = "${audience.expiresSecond}")
    private Long expiresSecond;

    /**
     * Reserved claims（保留），它的含义就像是编程语言的保留字一样，属于JWT标准里面规定的一些claim。JWT标准里面定好的claim有：
     * iss(Issuser)：代表这个JWT的签发主体；
     * sub(Subject)：代表这个JWT的主体，即它的所有人；
     * aud(Audience)：代表这个JWT的接收对象；
     * exp(Expiration time)：是一个时间戳，代表这个JWT的过期时间；
     * nbf(Not Before)：是一个时间戳，代表这个JWT生效的开始时间，意味着在这个时间之前验证JWT是会失败的；
     * iat(Issued at)：是一个时间戳，代表这个JWT的签发时间；
     * jti(JWT ID)：是JWT的唯一标识。
     *
     * @param request
     * @param response
     * @param chain
     * @throws IOException
     * @throws ServletException
     */
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain)
            throws ServletException, IOException {
        //得到请求头信息authorization信息
        final String authHeader = request.getHeader(tokenHeader);

        log.error("传递过来的token为:" + authHeader);

        //请求头 'Authorization': tokenHead + token
        if (authHeader != null && authHeader.startsWith(tokenHead)) {

            final String token = authHeader.substring(tokenHead.length());

            //判断token是否过期
            if (jwtHelper.isExpiration(token, audience.getBase64Secret())) {
                log.info("token已过期，需要重新登录");
                chain.doFilter(request, response);
                return;
            } else if(jwtHelper.isNeedFresh(token, audience.getBase64Secret())){
                //刷新token过期时间
                String newToken = jwtHelper.refreshToken(token, audience.getBase64Secret(), expiresSecond*1000);
                log.info("token还差5分钟过期，刷新token");
                response.setHeader(tokenHeader,tokenHead+newToken);
            }

            String userName = jwtHelper.getUsername(token, audience.getBase64Secret());
            Long userId = jwtHelper.getUserId(token, audience.getBase64Secret());

            //把userId,userName存储到request中
            request.setAttribute("userId", userId);
            request.setAttribute("userName", userName);
            log.info("解析出来用户 : " + userName);
            log.info("解析出来的用户Uid : " + userId);

            if (userName != null && SecurityContextHolder.getContext().getAuthentication() == null) {

                UserDetails userDetails = this.userDetailsService.loadUserByUsername(userName);

                //账号是否可用
                if(!userDetails.isEnabled()){
                    log.info(AlertExceptionConst.ACCOUNT_FROZEN);
                    chain.doFilter(request,response);
                    return;
                }

                if (jwtHelper.validateToken(token, userDetails, audience.getBase64Secret())) {
                    UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(
                            userDetails, null, userDetails.getAuthorities());
                    authentication.setDetails(new WebAuthenticationDetailsSource().buildDetails(
                            request));
                    log.info("authenticated user " + userName + ", setting security context");
                    //以后可以security中取得SecurityUser信息
                    SecurityContextHolder.getContext().setAuthentication(authentication);
                }
            }
        }
        chain.doFilter(request, response);
    }
}
		

