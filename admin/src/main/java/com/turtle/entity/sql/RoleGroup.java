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
@TableName("tbg_role_group")
public class RoleGroup {
    private Long id;
    private String name;
    private LocalDateTime createTime;
    private LocalDateTime updateTime;
    private Boolean isDelete;
}
