package com.turtle.admin.config.security;

import com.turtle.admin.entity.User;
import com.turtle.common.security.SecurityUser;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.util.List;
import java.util.stream.Collectors;

public final class SecurityUserFactory {

    private SecurityUserFactory() {
    }

    public static SecurityUser create(User user,List<String> authorities) {
        boolean enabled = user.getIsDelete() == 1;
        return new SecurityUser(
                user.getId(),
                user.getUserName(),
                user.getPassword(),
                user.getStatus(),
                enabled,
                mapToGrantedAuthorities(authorities)
        );
    }

    private static List<GrantedAuthority> mapToGrantedAuthorities(List<String> authorities) {
        return authorities.stream()
                .map(SimpleGrantedAuthority::new)
                .collect(Collectors.toList());
    }

}
