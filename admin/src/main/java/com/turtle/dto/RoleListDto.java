package com.turtle.dto;

import lombok.Builder;
import lombok.Data;
import lombok.experimental.Accessors;

import java.util.List;

/**
 * @author lijiayu
 * @date 2020/4/27
 * @description
 */
@Data
@Builder
@Accessors(chain = true)
public class RoleListDto {
    private Long id;
    private String name;
    private List<RoleDto> roleList;
}
