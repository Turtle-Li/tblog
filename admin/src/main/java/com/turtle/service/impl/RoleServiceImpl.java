package com.turtle.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.turtle.constant.SqlConf;
import com.turtle.dto.*;
import com.turtle.entity.sql.Role;
import com.turtle.entity.sql.RoleGroup;
import com.turtle.entity.sql.UserRole;
import com.turtle.exception.UserAlertException;
import com.turtle.mapper.RoleGroupMapper;
import com.turtle.mapper.RoleMapper;
import com.turtle.mapper.UserRoleMapper;
import com.turtle.service.RoleService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * @author lijiayu
 * @date 2020/4/27
 * @description
 */
@Service
@Slf4j
public class RoleServiceImpl implements RoleService {
    @Autowired
    private RoleMapper roleMapper;
    @Autowired
    private RoleGroupMapper roleGroupMapper;
    @Autowired
    private UserRoleMapper userRoleMapper;

    @Override
    public List<RoleListDto> list() {
        List<Role> roleList = roleMapper.selectList(null);
        Map<Long, List<Role>> roleMap = roleList.stream().collect(Collectors.groupingBy(Role::getGroupId));

        List<RoleGroup> roleGroupList = roleGroupMapper.selectList(null);
        return roleGroupList.stream().map(x ->
                RoleListDto.builder()
                        .id(x.getId())
                        .name(x.getName())
                        .roleList(roleMap.get(x.getId()) == null ? new ArrayList<>() :
                                roleMap.get(x.getId()).stream().map(y ->
                                        RoleDto.builder()
                                                .id(y.getId())
                                                .roleName(y.getRoleName())
                                                .name(y.getName())
                                                .build())
                                        .collect(Collectors.toList()))
                        .build())
                .collect(Collectors.toList());
    }

    @Override
    public List<Role> getMyRoleList(Long id) {
        return roleMapper.selectMyRoleList(id);
    }

    @Override
    public void doGroup(RoleGroupParam param) {
        QueryWrapper<RoleGroup> qw = new QueryWrapper<RoleGroup>()
                .eq(SqlConf.NAME, param.getName())
                .ne(param.getId() != null, SqlConf.ID, param.getId());
        if (roleGroupMapper.selectOne(qw) != null) {
            throw new UserAlertException("角色组名称已存在！");
        }
        RoleGroup roleGroup = RoleGroup
                .builder()
                .name(param.getName())
                .build();
        if (param.getId() == null) {
            roleGroupMapper.insert(roleGroup);
        } else {
            roleGroup.setId(param.getId());
            roleGroupMapper.updateById(roleGroup);
        }
    }

    @Override
    public void doRole(RoleParam param) {
        QueryWrapper<Role> qw = new QueryWrapper<Role>()
                .eq(SqlConf.NAME, param.getName())
                .ne(param.getId() != null, SqlConf.ID, param.getId());
        if (roleMapper.selectOne(qw) != null) {
            throw new UserAlertException("角色名称已存在！");
        }
        Role role = Role.builder()
                .roleName(param.getRoleName())
                .name(param.getName())
                .groupId(param.getGroupId())
                .build();
        if (param.getId() == null) {
            roleMapper.insert(role);
        } else {
            role.setId(param.getId());
            roleMapper.updateById(role);
        }
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void assign(AssignRoleParam param) {
        Set<Long> roleIds = param.getRoleIds();
        List<UserRole> userRoles = roleIds.stream().map(x ->
                UserRole.builder()
                        .roleId(x)
                        .userId(param.getUserId())
                        .build())
                .collect(Collectors.toList());
        userRoleMapper.delete(new QueryWrapper<UserRole>().eq(SqlConf.USERID, param.getUserId()));
        userRoleMapper.insertBatchSomeColumn(userRoles);
    }
}
