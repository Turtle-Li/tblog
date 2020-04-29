package com.turtle.constant;

/**
 * 前端定义code
 */
public final class ResponseConst {
  private ResponseConst(){}

  /**
   * UserAlertException 用户提示code
   */
  public static final String USER_ALTER_ERR = "1";
  /**
   * FrontRequestException 前端错误code
   */
  public static final String FRONT_REQUEST_ERR = "2";
  /**
   * LogoutClientException 用户需要重新登录code
   */
  public static final String USER_LOGOUT_ERR = "3";
  /**
   * UserPopupException 用户弹窗提示错误code
   */
  public static final  String USER_POPUP_ERR = "4";
  /**
   * RpcRequestException rpc请求错误
   */
  public static final String RPC_REQUEST_ERR = "4400";
}
