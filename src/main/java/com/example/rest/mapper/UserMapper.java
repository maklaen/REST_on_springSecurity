package com.example.rest.mapper;


import com.example.rest.dto.auth.request.RegisterRequestDto;
import com.example.rest.dto.user.response.ProfileResponseDto;
import com.example.rest.entity.User;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
public class UserMapper {

    private final PasswordEncoder passwordEncoder;

    public UserMapper(PasswordEncoder passwordEncoder) {
        this.passwordEncoder = passwordEncoder;
    }

    public ProfileResponseDto convertToProfileResponse(User user) {
        return new ProfileResponseDto(
                user.getUsername()
        );
    }

    public User convertToUserFromRegisterRequest(RegisterRequestDto requestDto) {
        return new User(
                requestDto.getUsername(),
                passwordEncoder.encode(requestDto.getPassword())
        );
    }
}
