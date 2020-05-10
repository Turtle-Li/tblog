package com.turtle.entity.sql;

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
@TableName("tbg_favorite_blog")
public class FavoriteBlog {
    private Long id;
    private Long userId;
    private Long blogId;
    private LocalDateTime dateTime;
}
