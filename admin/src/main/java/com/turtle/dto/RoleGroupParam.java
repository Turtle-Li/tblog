package com.turtle.dto;

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
public class RoleGroupParam {
    private Long id;
    @NotBlank
    @ApiModelProperty(value = "角色组名称")
    private String name;
}
