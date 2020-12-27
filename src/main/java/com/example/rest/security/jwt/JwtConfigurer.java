package com.example.rest.security.jwt;


import com.example.rest.security.CustomUserDetailsService;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.SecurityConfigurerAdapter;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.DefaultSecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
public class JwtConfigurer extends SecurityConfigurerAdapter<DefaultSecurityFilterChain, HttpSecurity> {

    public JwtConfigurer(JwtProvider jwtProvider, CustomUserDetailsService customUserDetailsService) {
        this.jwtProvider = jwtProvider;
        this.customUserDetailsService = customUserDetailsService;
    }

    private JwtProvider jwtProvider;

    private CustomUserDetailsService customUserDetailsService;

    @Override
    public void configure(HttpSecurity builder) {
        JwtFilter customFilter = new JwtFilter(jwtProvider, customUserDetailsService);
        builder.addFilterBefore(customFilter, UsernamePasswordAuthenticationFilter.class);
    }

}
