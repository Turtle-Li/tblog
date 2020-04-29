package com.turtle.exception;

import com.turtle.constant.ResponseConst;

/**
 * 鉴权错误客户端自动登出 400 {"code":"3","message":"exception.message"}
 */
public class UserLogoutException extends BizException {
  public UserLogoutException(String message) {
    super(ResponseConst.USER_LOGOUT_ERR, message);
  }
}
