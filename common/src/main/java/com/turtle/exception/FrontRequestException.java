package com.turtle.exception;

import com.turtle.constant.ResponseConst;

/**
 * 前端错误 400 {"code":"2","message":"exception.message"}
 */
public class FrontRequestException extends BizException {

  private static final long serialVersionUID = 4253363752677096186L;

  public FrontRequestException( String message) {
    super(ResponseConst.FRONT_REQUEST_ERR, message);
  }
}
