package com.example.rest.service;


import com.example.rest.dto.auth.request.AuthRequestDto;
import com.example.rest.dto.auth.request.RegisterRequestDto;
import com.example.rest.dto.user.response.ProfileResponseDto;
import org.springframework.http.ResponseEntity;


public interface AuthService {

    ResponseEntity<?> login(AuthRequestDto requestDto);

    ProfileResponseDto register(RegisterRequestDto requestDto);
}
