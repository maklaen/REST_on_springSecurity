package com.example.rest.rest.auth;


import com.example.rest.dto.auth.request.AuthRequestDto;
import com.example.rest.dto.auth.request.RegisterRequestDto;
import com.example.rest.dto.user.response.ProfileResponseDto;
import com.example.rest.service.AuthService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@CrossOrigin(origins = "*", allowedHeaders = "*")
@RequestMapping(value = "/")
public class AuthRest {

    private final AuthService authService;

    public AuthRest(AuthService authService) {
        this.authService = authService;
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(
            @RequestBody AuthRequestDto requestDto
    ) {
        return authService.login(requestDto);
    }

    @PostMapping("/register")
    public ProfileResponseDto register(
            @RequestBody RegisterRequestDto requestDto
    ) {
        return authService.register(requestDto);
    }
}
