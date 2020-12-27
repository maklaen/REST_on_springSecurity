package com.example.rest.security.jwt;

import com.example.rest.security.CustomUserDetails;
import com.example.rest.security.CustomUserDetailsService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.lang.NonNull;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Slf4j
public class JwtFilter extends OncePerRequestFilter {

private final JwtProvider jwtProvider;

    private final CustomUserDetailsService customUserDetailsService;

    public JwtFilter(
            JwtProvider jwtProvider,
            CustomUserDetailsService customUserDetailsService
    ) {
        super();
        this.jwtProvider = jwtProvider;
        this.customUserDetailsService = customUserDetailsService;
    }

    @Override
    protected void doFilterInternal(
            @NonNull HttpServletRequest request,
            @NonNull HttpServletResponse response,
            @NonNull FilterChain filterChain
    ) throws IOException, ServletException {
        String token = jwtProvider.getTokenFromRequest(request);
        if (token != null && jwtProvider.isValidToken(token)) {
            String userEmail = jwtProvider.getUsernameFromToken(token);
            if (userEmail != null && !userEmail.isEmpty()) {
                CustomUserDetails customUserDetails = customUserDetailsService.loadUserByUsername(userEmail);
                if (customUserDetails != null) {
                    UsernamePasswordAuthenticationToken auth = new UsernamePasswordAuthenticationToken(
                            customUserDetails,
                            null,
                            customUserDetails.getAuthorities());
                    SecurityContextHolder.getContext().setAuthentication(auth);
                } else {
                    log.error("Пользователь не найден!");
                }
            } else {
                log.error("Логин пользователя пуст!.");
            }
        } else {
            log.error("Токен отсутствует или некорректный.");
        }
        filterChain.doFilter(request, response);
    }

}
