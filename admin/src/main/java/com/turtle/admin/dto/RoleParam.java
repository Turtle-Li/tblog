package com.turtle.admin.dto;

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
public class RoleParam {
    private Long id;
    @NotBlank
    private String roleName;
    @NotBlank
    private String name;
    @NotNull
    private Long groupId;
}
