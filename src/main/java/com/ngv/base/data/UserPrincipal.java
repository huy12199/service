package com.ngv.base.data;

import java.util.ArrayList;
import java.util.Collection;
import lombok.Getter;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

@Getter
public class UserPrincipal implements UserDetails {

    private final String username;
    private final Long id;

    private final Long roleId;

    private final Collection<SimpleGrantedAuthority> authorities;

    public UserPrincipal(String username, Long id, Long roleId) {
        this.username = username;
        this.id = id;
        this.roleId = roleId;
        authorities = new ArrayList<>();
        authorities.add(new SimpleGrantedAuthority("ROLE_" + roleId));
    }

    public Long getUserId() {
        return id;
    }

    public Long getRoleId() {
        return roleId;
    }

    @Override
    public String getPassword() {
        return null;
    }

    @Override
    public String getUsername() {
        return username;
    }

    @Override
    public boolean isAccountNonExpired() {
        return false;
    }

    @Override
    public boolean isAccountNonLocked() {
        return false;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return false;
    }

    @Override
    public boolean isEnabled() {
        return false;
    }
}
