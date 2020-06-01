package com.turtle.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.turtle.dto.UserUpdateInfoParam;
import com.turtle.entity.sql.User;
import com.turtle.vo.ResultBody;

/**
 * 用户业务接口类
 */
public interface UserService   extends IService<User> {
    User getUserByUserName(String userName);

    User getUserByEmail(String email);

    void updateInfo(UserUpdateInfoParam userUpdateInfoParam);

    ResultBody getByTokenInfo(String token);
}
