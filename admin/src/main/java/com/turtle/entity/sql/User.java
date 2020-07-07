package com.turtle.entity.sql;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.*;
import lombok.experimental.Accessors;

import java.time.LocalDateTime;

/**
 * @author lijiayu
 * @date 2020/1/15
 * @description
 */
@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
@Accessors(chain = true)
@TableName("tbg_user")
public class User {
    private Long id;
    private String userName;
    private String name;
    /**
     * 头像
     */
    private String avatar;
    private String email;
    private String password;
    private Integer status;
    /**
     * 性别
     */
    private Integer gender;
    /**
     * 个性签名
     */
    private String signature;

    private LocalDateTime createTime;

    private LocalDateTime updateTime;

    private Boolean isDelete;
}
