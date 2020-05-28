package com.turtle.dto;

import lombok.Data;
import lombok.experimental.Accessors;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import java.io.Serializable;

/**
 * @author lijiayu
 * @date 2020/5/28
 * @description
 */
@Data
@Accessors(chain = true)
public class UserForgetEmailParam implements Serializable {
    @NotBlank
    private String userName;

    @Email
    private String email;
}
