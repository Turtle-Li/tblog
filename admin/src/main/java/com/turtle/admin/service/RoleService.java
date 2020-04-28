package com.turtle.admin.service;

import com.turtle.admin.dto.AssignRoleParam;
import com.turtle.admin.dto.RoleGroupParam;
import com.turtle.admin.dto.RoleListDto;
import com.turtle.admin.dto.RoleParam;
import com.turtle.admin.entity.Role;
import com.turtle.common.vo.ResultBody;

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
