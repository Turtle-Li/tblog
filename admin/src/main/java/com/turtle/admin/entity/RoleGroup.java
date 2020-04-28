package com.turtle.admin.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.turtle.common.entity.sql.BaseEntity;
import lombok.Data;
import lombok.experimental.Accessors;

/**
 * @author lijiayu
 * @date 2020/4/27
 * @description
 */
@Data
@Accessors(chain = true)
@TableName("tbg_role_group")
public class RoleGroup extends BaseEntity<RoleGroup> {
    private String name;
}
