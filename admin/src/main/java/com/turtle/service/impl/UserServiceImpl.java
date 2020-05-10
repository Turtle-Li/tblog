package com.turtle.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.turtle.dto.TokenUserDto;
import com.turtle.dto.UserUpdateInfoParam;
import com.turtle.entity.sql.User;
import com.turtle.jwt.Audience;
import com.turtle.jwt.JwtHelper;
import com.turtle.mapper.UserMapper;
import com.turtle.service.UserService;
import com.turtle.vo.ResultBody;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

/**
 * @Auther: fuzongle
 * @Date: 2020/5/10 20:35
 * @Description:
 */
@Service
@Slf4j
public class UserServiceImpl extends ServiceImpl<UserMapper,User> implements UserService {

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private Audience audience;

    @Autowired
    private JwtHelper jwtHelper;

    @Override
    public ResultBody updateInfo(UserUpdateInfoParam userInfoParam) {
        PasswordEncoder encoder = new BCryptPasswordEncoder();
        User user = new User().setName(userInfoParam.getName())
                .setAvatar(userInfoParam.getAvatar())
                .setGender(userInfoParam.getGender())
                .setPassword(encoder.encode(userInfoParam.getPassword()));
        user.setId(userInfoParam.getId());
        user.setUpdateTime(LocalDateTime.now());
        userMapper.updateById(user);
        return ResultBody.success();
    }

    @Override
    public ResultBody getByTokenInfo(String token) {
        Long userId = jwtHelper.getUserId(token, audience.getBase64Secret());
        User user = userMapper.selectById(userId);
        TokenUserDto tokenUserDto = new TokenUserDto().setId(userId)
                .setAvatar(user.getAvatar())
                .setName(user.getName())
                .setUserName(user.getUserName());
        return ResultBody.result(tokenUserDto);
    }
}