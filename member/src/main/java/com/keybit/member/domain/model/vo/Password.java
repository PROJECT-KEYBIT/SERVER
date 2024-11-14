package com.keybit.member.domain.model.vo;

import com.keybit.member.common.encoder.EncoderAdaptor;
import jakarta.persistence.Embeddable;
import lombok.Getter;

@Getter
@Embeddable
public class Password {

    private String password;

    public static Password create(String password) {
        String encodedPassword = EncoderAdaptor.encode(password);
        return new Password(encodedPassword);
    }

    protected Password() {}

    private Password(String password) {
        this.password = password;
    }
}
