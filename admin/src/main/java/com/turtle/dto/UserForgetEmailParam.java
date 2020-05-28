package com.turtle.dto;

import lombok.Data;
import lombok.experimental.Accessors;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

/**
 * @author lijiayu
 * @date 2020/5/28
 * @description
 */
@Data
@Accessors(chain = true)
public class UserForgetEmailParam {
    @NotBlank
    private String username;

    @Email
    private String email;
}
