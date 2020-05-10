package com.turtle.config.security;

import com.turtle.constant.UserConst;
import com.turtle.entity.sql.User;
import com.turtle.security.SecurityUser;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.util.List;
import java.util.stream.Collectors;

public final class SecurityUserFactory {

    private SecurityUserFactory() {
    }

    public static SecurityUser create(User user,List<String> roleNames) {
        boolean enabled = user.getStatus() != UserConst.STATUS_FREEZE;
        return new SecurityUser(
                user.getId(),
                user.getUserName(),
                user.getPassword(),
                user.getStatus(),
                enabled,
                mapToGrantedAuthorities(roleNames)
        );
    }

    private static List<GrantedAuthority> mapToGrantedAuthorities(List<String> roleNames) {
        return roleNames.stream()
                .map(SimpleGrantedAuthority::new)
                .collect(Collectors.toList());
    }

}
