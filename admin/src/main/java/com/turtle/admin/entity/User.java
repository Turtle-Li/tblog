package com.turtle.admin.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.experimental.Accessors;

import java.time.LocalDateTime;

/**
 * @author lijiayu
 * @date 2020/1/15
 * @description
 */
@Data
@Accessors(chain = true)
@TableName("tbg_user")
public class User {
    private long id;
    private String username;
    private String name;
    private String avatar;
    private String email;
    private String password;
    private int status;
    private LocalDateTime created;
    private LocalDateTime updated;
    private LocalDateTime lastLogin;
    private int gender;
    private int comments;
    private int posts;
    private String signature;
}
