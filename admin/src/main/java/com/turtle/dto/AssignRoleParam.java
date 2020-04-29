package com.turtle.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.experimental.Accessors;

import javax.validation.constraints.NotNull;
import java.util.Set;

/**
 * @author lijiayu
 * @date 2020/4/28
 * @description
 */
@Data
@Accessors(chain = true)
@ApiModel
public class AssignRoleParam {
    @NotNull
    @ApiModelProperty("用户id")
    private Long userId;
    @NotNull
    @ApiModelProperty("角色id")
    private Set<Long> roleIds;
}
