package com.turtle.entity.sql;

import lombok.Data;

import java.time.LocalDateTime;

/**
 * @author lijiayu
 * @date 2020/4/17
 * @description
 */
@Data
public class BaseEntity<T> {
    private Long id;

    private LocalDateTime createTime;

    private LocalDateTime updateTime;

    private Integer isDelete;
}
