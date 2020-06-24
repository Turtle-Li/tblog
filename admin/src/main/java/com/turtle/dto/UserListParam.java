package com.turtle.dto;

import io.swagger.annotations.ApiModelProperty;
import io.swagger.annotations.ApiOperation;
import lombok.Data;
import lombok.experimental.Accessors;

import java.time.LocalDateTime;

/**
 * @author lijiayu
 * @date 2020/6/2
 * @description
 */
@Data
@Accessors(chain = true)
public class UserListParam {
    @ApiModelProperty("用户名或昵称")
    private String name;
    @ApiModelProperty("状态 0：正常   1：vip  2：冻结")
    private Integer status;
    @ApiModelProperty("注册开始时间")
    private LocalDateTime startTime;
    @ApiModelProperty("注册结束时间")
    private LocalDateTime endTime;
    private Integer page = 1;
    private Integer size = 10;
}
