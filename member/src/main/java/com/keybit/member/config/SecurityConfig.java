package com.keybit.member.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.keybit.member.application.outputport.MemberOutputPort;
import com.keybit.member.common.jwt.JwtIssuer;
import com.keybit.member.common.jwt.LoginFilter;
import com.keybit.member.domain.model.Member;
import com.keybit.member.framework.web.dto.security.AuthenticatedAccount;
import com.keybit.member.framework.web.dto.security.UserPrincipal;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@RequiredArgsConstructor
public class SecurityConfig {

    private final AuthenticationConfiguration authenticationConfiguration;
    private final MemberOutputPort outputPort;
    private final ObjectMapper objectMapper;
    private final JwtIssuer jwtIssuer;

    private final String[] notLoggedAllowPage = new String[]{
            "/login",
            "api/member/join"
    };

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {

        http.authorizeHttpRequests(auth -> auth
                        .requestMatchers(HttpMethod.GET).permitAll()
                        .requestMatchers(notLoggedAllowPage).permitAll() // 비로그인 유저
                        .anyRequest().authenticated())
                .sessionManagement(auth -> auth.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .csrf(AbstractHttpConfigurer::disable)
                .cors(AbstractHttpConfigurer::disable)
                .formLogin(AbstractHttpConfigurer::disable)
                .httpBasic(AbstractHttpConfigurer::disable)
                .addFilterAt(new LoginFilter(authenticationManager(authenticationConfiguration), objectMapper, jwtIssuer), UsernamePasswordAuthenticationFilter.class);

        return http.build();
    }

    @Bean
    public PasswordEncoder passwordEncoder () {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration configuration) throws Exception {
        return configuration.getAuthenticationManager();
    }

    @Bean
    public UserDetailsService userDetailsService() {
        return username -> {
            Member member = outputPort.loadMemberById(username)
                    .orElseThrow(() -> new UsernameNotFoundException(username + " 없는 아이디입니다."));

            AuthenticatedAccount authenticatedAccount = AuthenticatedAccount.builder()
                    .memId(member.getId())
                    .password(member.getPassword())
                    .roles(member.getRoles())
                    .build();

            return new UserPrincipal(authenticatedAccount);
        };
    }
}
