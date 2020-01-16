package com.turtle.common.exception;


import com.turtle.common.constant.ResponseConst;

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
