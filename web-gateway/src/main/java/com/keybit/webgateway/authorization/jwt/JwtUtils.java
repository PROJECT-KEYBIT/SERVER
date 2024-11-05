package com.keybit.webgateway.authorization.jwt;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.JWTVerifier;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class JwtUtils implements InitializingBean {

    private final JwtDecoder jwtDecoder;
    private final JwtProperties properties;
    private final JwtToTokenUserConverter converter;

    private Algorithm algorithm;
    private JWTVerifier jwtVerifier;

    @Override
    public void afterPropertiesSet() throws Exception {
        this.algorithm = Algorithm.HMAC512(properties.getSecretKey());
        this.jwtVerifier = JWT.require(algorithm).acceptLeeway(5).build();
    }

    public boolean isValid(String token) {
        try {
            jwtVerifier.verify(token);
            return true;
        } catch (RuntimeException e){
            return false;
        }
    }

    public TokenUser decode(String token) {
        return converter.convert(jwtDecoder.decode(token));
    }
}
