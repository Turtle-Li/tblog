package com.turtle.dto;

import lombok.Data;
import lombok.experimental.Accessors;

import javax.validation.constraints.NotBlank;

/**
 * @author lijiayu
 * @date 2020/5/29
 * @description
 */
@Data
@Accessors(chain = true)
public class UserChangePasswordParam {
    @NotBlank
    private String userName;
    @NotBlank
    private String password;
    @NotBlank
    private String sid;
}
