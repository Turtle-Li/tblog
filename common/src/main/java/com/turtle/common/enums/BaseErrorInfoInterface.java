package com.turtle.common.enums;

/**
 * @author lijiayu
 * @date 2020/1/16
 * @description
 */
public interface BaseErrorInfoInterface {
    /**
     * 错误码
     */
    String getResultCode();

    /**
     * 错误描述
     */
    String getResultMsg();
}
