package com.keybit.member.common.jwt;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.keybit.member.domain.model.vo.UserRole;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.Set;


@Component
@RequiredArgsConstructor
public class JwtIssuer {

    private final JwtProperties properties;

    public String issue(String memId, Set<UserRole> roles) {
        return JWT.create()
                .withSubject(String.valueOf(memId))
                .withExpiresAt(new Date(System.currentTimeMillis() + properties.getExpirationTime()))
                .withClaim("roles",
                        roles.stream()
                                .map(UserRole::getName)
                                .toList())
                .sign(Algorithm.HMAC256(properties.getSecretKey()));
    }
}
