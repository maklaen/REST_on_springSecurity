package com.example.rest.service;


import com.example.rest.dto.auth.Token;
import com.example.rest.dto.auth.request.AuthRequestDto;
import com.example.rest.dto.auth.request.RegisterRequestDto;
import com.example.rest.dto.auth.response.AuthResponseDto;
import com.example.rest.dto.user.response.ProfileResponseDto;
import com.example.rest.entity.User;
import com.example.rest.entity.UserRole;
import com.example.rest.mapper.UserMapper;
import com.example.rest.repository.RoleRepository;
import com.example.rest.repository.UserRepository;
import com.example.rest.security.jwt.JwtProvider;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Slf4j
public class AuthServiceImpl implements AuthService {

    private final UserRepository userRepository;

    private final RoleRepository roleRepository;

    private final JwtProvider jwtProvider;

    private final PasswordEncoder passwordEncoder;

    private final UserMapper userMapper;

    public AuthServiceImpl(
            UserRepository userRepository,
            JwtProvider jwtProvider,
            PasswordEncoder passwordEncoder,
            RoleRepository roleRepository,
            UserMapper userMapper
    ) {
        this.userRepository = userRepository;
        this.jwtProvider = jwtProvider;
        this.passwordEncoder = passwordEncoder;
        this.roleRepository = roleRepository;
        this.userMapper = userMapper;
    }

    @Override
    public ResponseEntity<?> login(AuthRequestDto requestDto) {
        boolean isSuccessLogin = findByUsernameAndPassword(requestDto.getUsername(), requestDto.getPassword());
        if (isSuccessLogin) {
            Token accessToken = jwtProvider.generateAccessToken(requestDto.getUsername());
            HttpHeaders responseHeaders = new HttpHeaders();
            AuthResponseDto responseDto = new AuthResponseDto(true, accessToken.getTokenValue());
            return ResponseEntity.ok().body(responseDto);
        } else {
            throw new IllegalArgumentException("Пользователь не найден");
        }
    }

    private boolean findByUsernameAndPassword(String email, String password) {
        User user = userRepository.findByUsername(email).orElse(null);
        if (user != null) {
            return passwordEncoder.matches(password, user.getPassword());
        }
        return false;
    }

    @Transactional
    @Override
    public ProfileResponseDto register(RegisterRequestDto requestDto) {
        if (isAvailableUsername(requestDto.getUsername())) {
            User user = userMapper.convertToUserFromRegisterRequest(requestDto);
            user = userRepository.save(user);
            UserRole role = new UserRole(user, "ROLE_USER");
            roleRepository.save(role);
            return userMapper.convertToProfileResponse(user);
        } else {
            throw new IllegalArgumentException("Некорректные данные.");
        }
    }

    private boolean isAvailableUsername(String username) {
        return userRepository.countByUsername(username) == 0;
    }
}
