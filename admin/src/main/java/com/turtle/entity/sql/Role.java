package com.turtle.entity.sql;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.experimental.Accessors;

/**
 * @author lijiayu
 * @date 2020/4/27
 * @description
 */
@Data
@Accessors(chain = true)
@TableName("tbg_role")
public class Role extends BaseEntity<Role> {
    /**
     * 角色标识
     */
    private String roleName;
    /**
     * 角色名称
     */
    private String name;
    /**
     * 角色组id
     */
    private Long groupId;
}
