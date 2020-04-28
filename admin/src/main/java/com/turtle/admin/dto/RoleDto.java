package com.turtle.admin.dto;

import lombok.Data;
import lombok.experimental.Accessors;

/**
 * @author lijiayu
 * @date 2020/4/27
 * @description
 */
@Data
@Accessors(chain = true)
public class RoleDto {
    private Long id;
    private String roleName;
    private String name;
}
