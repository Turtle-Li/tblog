package com.turtle.admin.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
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
@ApiModel
public class LoginParam {
    @NotBlank
    @ApiModelProperty(value = "用户名",required = true)
    private String userName;
    @NotBlank
    @ApiModelProperty(value = "密码",required = true)
    private String password;
    /**
     * 是否记住密码 1：记住  0：不记住
     */
    @ApiModelProperty(value = "是否记住密码")
    private int isRememberMe;
}
