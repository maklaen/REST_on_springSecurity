package com.example.rest.dto.auth;

import lombok.*;

@Data
@AllArgsConstructor
public class Token {

    private String tokenValue;
    private Long duration;
    private String secret;

    public Token(String tokenValue, Long duration) {
        this.tokenValue = tokenValue;
        this.duration = duration;
    }
}
