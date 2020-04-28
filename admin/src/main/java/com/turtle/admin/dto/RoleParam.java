package com.turtle.admin.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.experimental.Accessors;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

/**
 * @author lijiayu
 * @date 2020/4/28
 * @description
 */
@Data
@Accessors(chain = true)
@ApiModel
public class RoleParam {
    private Long id;
    @NotBlank
    @ApiModelProperty(value = "角色组标识")
    private String roleName;
    @NotBlank
    @ApiModelProperty("角色名称")
    private String name;
    @NotNull
    @ApiModelProperty("角色组id")
    private Long groupId;
}
