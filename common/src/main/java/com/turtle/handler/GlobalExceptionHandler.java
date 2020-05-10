package com.turtle.handler;

import com.turtle.enums.ExceptionEnum;
import com.turtle.exception.BizException;
import com.turtle.vo.ResultBody;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.servlet.http.HttpServletRequest;

/**
 * @author lijiayu
 * @date 2020/1/16
 * @description
 */
@RestControllerAdvice({"com.turtle.controller"})
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

    @ExceptionHandler(value =AccessDeniedException.class)
    public ResultBody exceptionHandler(HttpServletRequest req, AccessDeniedException e){
        log.error("A accessDeniedException has occurred！reason:",e);
        return ResultBody.error("407","暂无访问权限！");
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
