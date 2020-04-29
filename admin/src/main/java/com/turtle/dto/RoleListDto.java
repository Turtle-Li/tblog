package com.turtle.dto;

import lombok.Data;
import lombok.experimental.Accessors;

import java.util.List;

/**
 * @author lijiayu
 * @date 2020/4/27
 * @description
 */
@Data
@Accessors(chain = true)
public class RoleListDto {
    private Long id;
    private String name;
    private List<RoleDto> roleList;
}
