package com.turtle.admin.controller;

import com.turtle.admin.dto.*;
import com.turtle.admin.service.RoleService;
import com.turtle.common.vo.ResultBody;
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
public class RoleController {

    @Autowired
    private RoleService roleService;

    @GetMapping
    public List<RoleListDto> list(){
        return roleService.list();
    }

    @PostMapping("/group")
    public ResultBody doGroup(@RequestBody @Valid RoleGroupParam param){
        return roleService.doGroup(param);
    }

    @PostMapping
    public ResultBody doRole(@RequestBody @Valid RoleParam param){
        return roleService.doRole(param);
    }

    @PostMapping("/assign")
    public ResultBody assign(@RequestBody @Valid AssignRoleParam param){
        return roleService.assign(param);
    }
}
