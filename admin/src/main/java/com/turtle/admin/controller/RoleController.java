package com.turtle.admin.controller;

import com.turtle.admin.dto.*;
import com.turtle.admin.service.RoleService;
import com.turtle.common.vo.ResultBody;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

/**
 * @author lijiayu
 * @date 2020/4/27
 * @description
 */
@RestController
@RequestMapping("/role")
@PreAuthorize("hasRole('admin')")
@Api(tags = {"角色功能"})
public class RoleController {

    @Autowired
    private RoleService roleService;

    @GetMapping
    @ApiOperation(value = "获取所有角色列表")
    public List<RoleListDto> list(){
        return roleService.list();
    }

    @PostMapping("/group")
    @ApiOperation(value = "操作角色组")
    public ResultBody doGroup(@RequestBody @Valid RoleGroupParam param){
        return roleService.doGroup(param);
    }

    @PostMapping
    @ApiOperation("操作角色")
    public ResultBody doRole(@RequestBody @Valid RoleParam param){
        return roleService.doRole(param);
    }

    @PostMapping("/assign")
    @ApiOperation("分配角色")
    public ResultBody assign(@RequestBody @Valid AssignRoleParam param){
        return roleService.assign(param);
    }
}
