package com.turtle.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.turtle.constant.AlertExceptionConst;
import com.turtle.constant.SqlConf;
import com.turtle.dto.*;
import com.turtle.entity.sql.User;
import com.turtle.exception.UserAlertException;
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
    public User getUserByUserName(String userName) {
        QueryWrapper<User> qw = new QueryWrapper<User>().eq(SqlConf.USERNAME, userName);
        return userMapper.selectOne(qw);
    }

    @Override
    public User getUserByEmail(String email) {
        QueryWrapper<User> qw = new QueryWrapper<>();
        qw.eq("email",email);
        return userMapper.selectOne(qw);
    }

    @Override
    public UserDto get(Long id) {
        User user = userMapper.selectById(id);
        return UserDto.builder().id(user.getId()).build();
    }

    @Override
    public PageEntity<User> getList(UserListParam param) {
        Page<User> page = new Page<>(param.getPage(),param.getSize());
        IPage<User> iPage = userMapper.getListPage(page, param);
        return PageEntity.of(iPage.getTotal(),iPage.getRecords());
    }

    @Override
    public void updateStatus(Long id, Integer status) {
        User user = userMapper.selectById(id);
        if(user==null){
            throw new UserAlertException(AlertExceptionConst.NOT_FIND_USERID);
        }
        user.setStatus(status);
        userMapper.updateById(user);
    }

    @Override
    public void updateInfo(UserUpdateInfoParam param) {
        User user = new User().setName(param.getName())
                .setId(param.getId())
                .setAvatar(param.getAvatar())
                .setGender(param.getGender())
                .setSignature(param.getSignature());
        userMapper.updateById(user);
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