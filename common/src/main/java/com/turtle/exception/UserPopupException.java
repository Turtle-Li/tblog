package com.turtle.exception;


import com.turtle.constant.ResponseConst;

/**
 * created By DCG
 **/
public class UserPopupException extends BizException {
  /**
   * 用户错误，前端弹框提示
   */
  public UserPopupException(String message) {
    super(ResponseConst.USER_POPUP_ERR, message);
  }
}
