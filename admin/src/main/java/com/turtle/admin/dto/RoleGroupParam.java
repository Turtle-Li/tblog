package com.turtle.admin.dto;

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
public class RoleGroupParam {
    private Long id;
    @NotBlank
    private String name;
}
