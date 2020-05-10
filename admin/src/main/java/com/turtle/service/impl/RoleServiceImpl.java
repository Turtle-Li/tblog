package com.turtle.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.turtle.constant.SqlConf;
import com.turtle.dto.*;
import com.turtle.entity.sql.Role;
import com.turtle.entity.sql.RoleGroup;
import com.turtle.entity.sql.UserRole;
import com.turtle.mapper.RoleGroupMapper;
import com.turtle.mapper.RoleMapper;
import com.turtle.mapper.UserRoleMapper;
import com.turtle.service.RoleService;
import com.turtle.exception.UserAlertException;
import com.turtle.vo.ResultBody;
import io.seata.spring.annotation.GlobalTransactional;
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
                new RoleListDto()
                        .setId(x.getId())
                        .setName(x.getName())
                        .setRoleList(roleMap.get(x.getId()) == null ? new ArrayList<>() :
                                roleMap.get(x.getId()).stream().map(y ->
                                        new RoleDto()
                                                .setId(y.getId())
                                                .setRoleName(y.getRoleName())
                                                .setName(y.getName()))
                                        .collect(Collectors.toList())))
                .collect(Collectors.toList());
    }

    @Override
    public List<Role> getMyRoleList(Long id) {
        return roleMapper.selectMyRoleList(id);
    }

    @Override
    public ResultBody doGroup(RoleGroupParam param) {
        QueryWrapper<RoleGroup> qw = new QueryWrapper<RoleGroup>()
                .eq(SqlConf.NAME, param.getName())
                .ne(param.getId()!=null,SqlConf.ID,param.getId());
        if(roleGroupMapper.selectOne(qw) != null){
            throw new UserAlertException("角色组名称已存在！");
        }
        RoleGroup roleGroup = new RoleGroup()
                .setName(param.getName());
        if(param.getId() == null){
            roleGroupMapper.insert(roleGroup);
        }else{
            roleGroup.setId(param.getId());
            roleGroupMapper.updateById(roleGroup);
        }
        return ResultBody.success();
    }

    @Override
    public ResultBody doRole(RoleParam param) {
        QueryWrapper<Role> qw = new QueryWrapper<Role>()
                .eq(SqlConf.NAME,param.getName())
                .ne(param.getId()!=null,SqlConf.ID,param.getId());
        if(roleMapper.selectOne(qw) != null){
            throw new UserAlertException("角色名称已存在！");
        }
        Role role = new Role()
                .setRoleName(param.getRoleName())
                .setName(param.getName())
                .setGroupId(param.getGroupId());
        if(param.getId() == null){
            roleMapper.insert(role);
        }else{
            role.setId(param.getId());
            roleMapper.updateById(role);
        }
        return ResultBody.success();
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public ResultBody assign(AssignRoleParam param) {
        Set<Long> roleIds = param.getRoleIds();
        List<UserRole> userRoles = roleIds.stream().map(x ->
                new UserRole().setRoleId(x)
                        .setUserId(param.getUserId()))
                .collect(Collectors.toList());
        userRoleMapper.delete(new QueryWrapper<UserRole>().eq(SqlConf.USERID,param.getUserId()));
        userRoleMapper.insertBatchSomeColumn(userRoles);
        return ResultBody.success();
    }

    @Override
    public void test1() {
        userRoleMapper.insert(new UserRole().setRoleId(1L).setUserId(1L));
    }
}
