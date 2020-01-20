package com.turtle.admin.service;


import com.turtle.admin.dto.RegisterDto;
import com.turtle.admin.entity.User;
import com.turtle.common.vo.ResultBody;

import java.util.List;

/**
 * @author lijiayu
 * @date 2020/1/15
 * @description
 */
public interface LoginService {

    boolean checkName(String username);

    ResultBody sendCode(String email);

    ResultBody register(RegisterDto registerDto);
}
