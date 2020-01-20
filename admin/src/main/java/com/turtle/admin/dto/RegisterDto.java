package com.turtle.admin.dto;

import lombok.Data;
import lombok.experimental.Accessors;

/**
 * @author lijiayu
 * @date 2020/1/20
 * @description
 */
@Data
@Accessors(chain = true)
public class RegisterDto {
    private String username;
    private String password;
    private String email;
    private int code;
}
