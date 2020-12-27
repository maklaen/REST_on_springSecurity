package com.example.rest.dto.auth.request;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class RegisterRequestDto {

    private String username;

    private String password;

    @Override
    public String toString() {
        return "RegisterRequestDto{" +
                "username='" + username + '\'' +'}';
    }
}
