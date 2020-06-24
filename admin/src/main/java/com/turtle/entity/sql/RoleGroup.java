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
@Getter
@Setter
@SuperBuilder
@Accessors(chain = true)
@TableName("tbg_role_group")
public class RoleGroup extends BaseEntity<RoleGroup> {
    private String name;
}
