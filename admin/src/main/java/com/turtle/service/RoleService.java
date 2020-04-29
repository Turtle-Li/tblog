package com.turtle.service;

import com.turtle.dto.AssignRoleParam;
import com.turtle.dto.RoleGroupParam;
import com.turtle.dto.RoleListDto;
import com.turtle.dto.RoleParam;
import com.turtle.entity.Role;
import com.turtle.vo.ResultBody;

import java.util.List;

/**
 * @author lijiayu
 * @date 2020/4/27
 * @description
 */
public interface RoleService {
    /**
     * 获取所有角色列表
     * @return
     */
    List<RoleListDto> list();

    List<Role> getMyRoleList(Long id);

    ResultBody doGroup(RoleGroupParam param);

    ResultBody doRole(RoleParam param);

    ResultBody assign(AssignRoleParam param);
}
