package com.example.rest.security;


import com.example.rest.entity.User;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;

import static java.util.stream.Collectors.toList;

public class CustomUserDetails implements UserDetails {

    private String username;

    private String password;

    private Collection<? extends GrantedAuthority> grantedAuthorities;

    CustomUserDetails(User user) {
        username = user.getUsername();
        password = user.getPassword();
        grantedAuthorities = user.getRoles()
                .stream()
                .map(it -> new SimpleGrantedAuthority(it.getRole()))
                .collect(toList());
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return grantedAuthorities;
    }

    @Override
    public String getPassword() {
        return password;
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
