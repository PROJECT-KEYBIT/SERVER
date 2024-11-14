package com.keybit.member.config;

import com.keybit.member.common.encoder.EncoderAdaptor;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
@RequiredArgsConstructor
public class BCryptEncoderConfig {

    private final PasswordEncoder passwordEncoder;

    @Bean
    public InitializingBean encoderInitializer() {
        return () -> EncoderAdaptor.setPasswordEncoder(passwordEncoder);
    }
}
