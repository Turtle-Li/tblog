package com.turtle.common.handler;

import com.turtle.common.enums.ExceptionEnum;
import com.turtle.common.exception.BizException;
import com.turtle.common.vo.ResultBody;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

import javax.servlet.http.HttpServletRequest;
import java.security.SignatureException;

/**
 * @author lijiayu
 * @date 2020/1/16
 * @description
 */
@RestControllerAdvice
@Slf4j
public class GlobalExceptionHandler {
    /**
     * 处理自定义的业务异常
     * @param req
     * @param e
     * @return
     */
    @ExceptionHandler(value = BizException.class)
    public ResultBody bizExceptionHandler(HttpServletRequest req, BizException e){
        log.error("A businessException has occurred！reason：{}",e.getErrorMsg());
        return ResultBody.error(e.getErrorCode(),e.getErrorMsg());
    }

    /**
     * 处理空指针的异常
     * @param req
     * @param e
     * @return
     */
    @ExceptionHandler(value =NullPointerException.class)
    public ResultBody exceptionHandler(HttpServletRequest req, NullPointerException e){
        log.error("A nullPointerException has occurred！reason:",e);
        return ResultBody.error("500","NullPointerException");
    }

    /**
     * 处理其他异常
     * @param req
     * @param e
     * @return
     */
    @ExceptionHandler(value =Exception.class)
    public ResultBody exceptionHandler(HttpServletRequest req, Exception e){
        log.error("An exception has occurred！reason:",e);
        return ResultBody.error(ExceptionEnum.INTERNAL_SERVER_ERROR);
    }
}
