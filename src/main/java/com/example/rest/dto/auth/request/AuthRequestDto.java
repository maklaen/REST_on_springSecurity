package com.example.rest.dto.auth.request;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class AuthRequestDto {

    private String username;

    private String password;

    @Override
    public String toString() {
        return "AuthRequestDto{" +
                "username='" + username + '\'' +
                '}';
    }
}
