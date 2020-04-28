package com.turtle.admin.dto;

import lombok.Data;
import lombok.experimental.Accessors;

import javax.validation.constraints.NotBlank;

/**
 * @author lijiayu
 * @date 2020/4/27
 * @description
 */
@Data
@Accessors(chain = true)
public class LoginParam {
    @NotBlank
    private String userName;
    @NotBlank
    private String password;
    /**
     * 是否记住密码 1：记住  0：不记住
     */
    private int isRememberMe;
}
