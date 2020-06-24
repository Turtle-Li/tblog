package com.turtle.entity.sql;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Builder;
import lombok.Data;
import lombok.experimental.Accessors;

/**
 * @author lijiayu
 * @date 2020/4/27
 * @description
 */
@Builder
@Data
@Accessors(chain = true)
@TableName("tbg_user_role")
public class UserRole {
    private Long id;
    private Long userId;
    private Long roleId;
}
