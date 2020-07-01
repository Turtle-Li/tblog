package com.turtle.entity.sql;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;
import lombok.experimental.SuperBuilder;

/**
 * @author lijiayu
 * @date 2020/4/27
 * @description
 */
@Setter
@Getter
@SuperBuilder
@Accessors(chain = true)
@TableName("tbg_role")
public class Role extends BaseEntity {
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
