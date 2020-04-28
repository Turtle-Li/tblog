package com.turtle.admin.dto;

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
public class AssignRoleParam {
    @NotNull
    private Long userId;
    @NotNull
    private Set<Long> roleIds;
}
