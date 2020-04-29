package com.turtle.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.turtle.entity.Role;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author lijiayu
 * @date 2020/4/27
 * @description
 */
@Repository
public interface RoleMapper extends BaseMapper<Role> {
    /**
     * 获取我的角色列表
     * @param id
     * @return
     */
    List<Role> selectMyRoleList(@Param("id") Long id);
}
