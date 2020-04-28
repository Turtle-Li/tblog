package com.turtle.admin.dto;

import lombok.Data;
import lombok.experimental.Accessors;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

/**
 * @author lijiayu
 * @date 2020/1/20
 * @description
 */
@Data
@Accessors(chain = true)
public class RegisterParam {
    @NotBlank
    private String userName;
    @NotBlank
    private String passWord;
    @NotBlank
    @Email
    private String email;
    @NotBlank
    private String code;
}
