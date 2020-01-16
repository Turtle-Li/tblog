package com.turtle.common.exception;


import com.turtle.common.constant.ResponseConst;

/**
 * rpc请求错误 400 {"code":"4400","message":"exception.message"}
 */
public class RpcRequestException extends BizException {
  public RpcRequestException(String message) {
    super(ResponseConst.RPC_REQUEST_ERR, message);
  }
}
