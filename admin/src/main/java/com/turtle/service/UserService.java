package com.turtle.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.turtle.dto.PageEntity;
import com.turtle.dto.UserDto;
import com.turtle.dto.UserListParam;
import com.turtle.dto.UserUpdateInfoParam;
import com.turtle.entity.sql.User;

/**
 * 用户业务接口类
 */
public interface UserService extends IService<User> {
    User getUserByUserName(String userName);

    User getUserByEmail(String email);

    UserDto get(Long id);

    PageEntity<User> getList(UserListParam param);

    void updateStatus(Long id,Integer status);

    void updateInfo(UserUpdateInfoParam userUpdateInfoParam);

}
