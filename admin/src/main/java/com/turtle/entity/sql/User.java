package com.turtle.entity.sql;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.Accessors;
import lombok.experimental.SuperBuilder;

import java.time.LocalDateTime;

/**
 * @author lijiayu
 * @date 2020/1/15
 * @description
 */
@SuperBuilder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Accessors(chain = true)
@TableName("tbg_user")
public class User extends BaseEntity {
    private String userName;
    private String name;
    /**
     * 头像
     */
    private String avatar;
    private String email;
    private String password;
    private Integer status;
    private LocalDateTime lastLogin;
    /**
     * 性别
     */
    private Integer gender;
    /**
     * 评论数
     */
    private Integer comments;
    /**
     * 文章数
     */
    private Integer posts;
    /**
     * 个性签名
     */
    private String signature;
}
