package com.turtle.entity.sql;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Builder;
import lombok.Data;
import lombok.experimental.Accessors;

import java.time.LocalDateTime;

/**
 * @author lijiayu
 * @date 2020/4/27
 * @description
 */
@Data
@Builder
@Accessors(chain = true)
@TableName("tbg_role")
public class Role {
    private Long id;
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

    private LocalDateTime createTime;
    private LocalDateTime updateTime;
    private Boolean isDelete;
}
