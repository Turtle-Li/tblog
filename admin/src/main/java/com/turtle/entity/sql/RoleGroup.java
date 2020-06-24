package com.turtle.entity.sql;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

/**
 * @author lijiayu
 * @date 2020/4/27
 * @description
 */
@Getter
@Setter
@Builder
@Accessors(chain = true)
@TableName("tbg_role_group")
public class RoleGroup extends BaseEntity<RoleGroup> {
    private String name;
}
