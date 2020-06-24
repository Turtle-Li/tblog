package com.turtle.entity.sql;

import lombok.Data;
import lombok.experimental.SuperBuilder;

import java.time.LocalDateTime;

/**
 * @author lijiayu
 * @date 2020/4/17
 * @description
 */
@Data
@SuperBuilder(toBuilder = true)
public class BaseEntity<T> {
    private Long id;

    private LocalDateTime createTime;

    private LocalDateTime updateTime;

    private Integer isDelete;

}
