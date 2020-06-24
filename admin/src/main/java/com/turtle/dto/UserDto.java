package com.turtle.dto;

import lombok.Builder;
import lombok.Data;
import lombok.experimental.Accessors;

import java.time.LocalDateTime;

/**
 * @author lijiayu
 * @date 2020/6/1
 * @description
 */
@Data
@Accessors(chain = true)
@Builder
public class UserDto {
    private Long id;
    private String userName;
    private String name;
    private String email;
    private Integer status;
    private LocalDateTime lastLogin;
    private Integer gender;
    private Integer comments;
    private Integer posts;
    private String signature;
}