package com.turtle.exception;

import com.turtle.constant.ResponseConst;

/**
 * 用户请求错误
 * 向用户展示的异常错误对象 400 {"code":"1","message":"exception.message"}
 */
public class UserAlertException extends BizException {
  private static final long serialVersionUID = 9029716603851352332L;
  /**
   * 用户错误
   */
  public UserAlertException(String message) {
    super(ResponseConst.USER_ALTER_ERR, message);
  }
}
