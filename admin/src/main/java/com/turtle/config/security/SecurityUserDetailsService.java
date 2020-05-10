package com.turtle.config.security;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.turtle.constant.SqlConf;
import com.turtle.entity.sql.Role;
import com.turtle.entity.sql.User;
import com.turtle.service.LoginService;
import com.turtle.service.RoleService;
import com.turtle.exception.UserAlertException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class SecurityUserDetailsService implements UserDetailsService {

    @Autowired
    private LoginService loginService;
    @Autowired
    private RoleService roleService;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        QueryWrapper<User> qw = new QueryWrapper<>();
        qw.eq(SqlConf.USERNAME,username);

        User user = loginService.getOne(qw);
        if(user == null){
            throw new UserAlertException(String.format("找不到用户名为'%s'的用户",username));
        }
        List<Role> myRoleList = roleService.getMyRoleList(user.getId());
        //查询用户信息后创建
        return SecurityUserFactory.create(user,myRoleList.stream().map(x -> "ROLE_"+ x.getRoleName()).collect(Collectors.toList()));
    }
}
