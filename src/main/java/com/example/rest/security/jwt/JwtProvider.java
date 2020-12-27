package com.example.rest.security.jwt;

import com.example.rest.dto.auth.Token;
import io.jsonwebtoken.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import java.security.SecureRandom;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;

import static io.jsonwebtoken.SignatureAlgorithm.HS512;
import static org.springframework.util.StringUtils.hasText;

@Slf4j
@Component
public class JwtProvider {

    private static final SecureRandom random = new SecureRandom();

    private static final String AUTHORIZATION = "Authorization";

    private static final long CONVERT_TO_SECONDS = 24L * 60L * 60L;


    @Value("${jwt.secret}")
    private String jwtSecret;

    @Value("${jwt.refreshToken.expiration:15}")
    private long expRefreshToken;

    public Token generateAccessToken(String email) {
        Date date = Date.from(LocalDate.now().plusDays(7)
                .atStartOfDay(ZoneId.systemDefault()).toInstant());
        String tokenValue = Jwts.builder()
                .setSubject(email)
                .setExpiration(date)
                .setIssuedAt(new Date())
                .signWith(HS512, jwtSecret)
                .compact();
        return new Token(tokenValue, 7 * CONVERT_TO_SECONDS);
    }

    boolean isValidToken(String token) {
        try {
            Jwts.parser().setSigningKey(jwtSecret).parseClaimsJws(token);
            return true;
        } catch (ExpiredJwtException expEx) {
            log.error("Token expired");
        } catch (UnsupportedJwtException unsEx) {
            log.error("Unsupported jwt");
        } catch (MalformedJwtException mjEx) {
            log.error("Malformed jwt");
        } catch (SignatureException sEx) {
            log.error("Invalid signature");
        } catch (Exception e) {
            log.error("Invalid token");
        }
        return false;
    }

    @Nullable
    public String getTokenFromRequest(HttpServletRequest servletRequest) {
        String bearer = servletRequest.getHeader(AUTHORIZATION);
        if (hasText(bearer) && bearer.startsWith("Bearer")) {
            return bearer.substring(7);
        } else {
            return null;
        }
    }

    @Nullable
    public String getUsernameFromToken(String token) {
        try {
            Claims claims = Jwts.parser().setSigningKey(jwtSecret).parseClaimsJws(token).getBody();
            return claims.getSubject();
        } catch (Exception ex) {
            return null;
        }
    }

}
