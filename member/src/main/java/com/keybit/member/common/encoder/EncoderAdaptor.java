package com.keybit.member.common.encoder;

import org.springframework.security.crypto.password.PasswordEncoder;

public class EncoderAdaptor {

    private static PasswordEncoder passwordEncoder;

    public static void setPasswordEncoder(PasswordEncoder passwordEncoder) {
        EncoderAdaptor.passwordEncoder = passwordEncoder;
    }

    public static String encode(String target) {
        return passwordEncoder.encode(target);
    }
}
