package com.keybit.member.common.jwt;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.keybit.member.framework.web.dto.request.LoginMemberRequest;
import com.keybit.member.framework.web.dto.security.UserPrincipal;
import jakarta.servlet.FilterChain;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationServiceException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.util.StreamUtils;

import java.nio.charset.StandardCharsets;
import java.util.ArrayList;

@Slf4j
@RequiredArgsConstructor
public class LoginFilter extends UsernamePasswordAuthenticationFilter {

    private final AuthenticationManager authenticationManager;
    private final ObjectMapper objectMapper;

    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) {
        checkRequestContentType(request);
        return authenticationManager.authenticate(createAuthenticationToken(request));
    }

    private UsernamePasswordAuthenticationToken createAuthenticationToken(HttpServletRequest request) {
        LoginMemberRequest loginDto = convertTo(request, LoginMemberRequest.class);
        return new UsernamePasswordAuthenticationToken(loginDto.getUsername(), loginDto.getPassword(), new ArrayList<>());
    }

    private <T> T convertTo(HttpServletRequest request, Class<T> valueType) {
        T t = null;

        try {
            String messageBody = StreamUtils.copyToString(request.getInputStream(), StandardCharsets.UTF_8);
            t = objectMapper.readValue(messageBody, valueType);
        } catch (Exception ignore) {}

        return t;
    }

    private static void checkRequestContentType(HttpServletRequest request) {
        if(request.getContentType() == null || !request.getContentType().equals(MediaType.APPLICATION_JSON_VALUE))
            throw new AuthenticationServiceException("Authentication Content-Type not supported: " + request.getContentType());
    }

    @Override
    protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response, FilterChain chain, Authentication authentication) {
        UserPrincipal principal = (UserPrincipal) authentication.getPrincipal();
        log.info("user: [{}]", principal);
    }

    @Override
    protected void unsuccessfulAuthentication(HttpServletRequest request, HttpServletResponse response, AuthenticationException exception) {
        log.info("로그인 실패!");
    }
}
