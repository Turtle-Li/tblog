package com.turtle.entity.sql;

import com.baomidou.mybatisplus.annotation.TableField;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import java.time.LocalDateTime;

/**
 * @author lijiayu
 * @date 2020/4/17
 * @description
 */
@Data
@SuperBuilder
@NoArgsConstructor
public class BaseEntity {
    private Long id;

    private LocalDateTime createTime;

    @TableField(update = "now()")
    private LocalDateTime updateTime;

    private Integer isDelete;

}
