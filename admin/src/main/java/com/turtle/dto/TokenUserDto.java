package com.turtle.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.experimental.Accessors;

/**
 * @Auther: fuzongle
 * @Date: 2020/5/10 22:52
 * @Description:通过Token获取用户信息
 */
@Data
@Accessors(chain = true)
public class TokenUserDto {

    @ApiModelProperty(value = "用户id")
    private Long id;

    @ApiModelProperty(value = "呢称")
    private String name;

    @ApiModelProperty(value = "头像")
    private String avatar;

    @ApiModelProperty(value = "用户名")
    private String userName;
}