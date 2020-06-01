package com.turtle.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.experimental.Accessors;

/**
 * @Auther: fuzongle
 * @Date: 2020/5/10 19:14
 * @Description:
 */
@Data
@Accessors(chain = true)
@ApiModel
public class UserUpdateInfoParam {


    @ApiModelProperty(value = "用户id")
    private Long id;

    @ApiModelProperty(value = "呢称")
    private String name;

    @ApiModelProperty(value = "头像")
    private String avatar;

    @ApiModelProperty(value = "性别")
    private Integer gender;

    @ApiModelProperty(value = "个性签名")
    private String signature;


}