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
@TableName("tbg_collect_blog")
public class CollectBlog {
    private Long id;
    private Long userId;
    private Long collectionId;
    private Long blogId;
    private LocalDateTime dateTime;
}
