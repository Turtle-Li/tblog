package com.turtle.entity.sql.sql;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.experimental.Accessors;

import java.time.LocalDateTime;

/**
 * @author lijiayu
 * @date 2020/5/8
 * @description
 */
@Data
@Accessors(chain = true)
@TableName("tbg_collection")
public class Collection {
    private Long id;
    private Long userId;
    private Long blogId;
    private LocalDateTime dateTime;
}
