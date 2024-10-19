package com.keybit.member.domain.model.vo;

import jakarta.persistence.Embeddable;
import lombok.Getter;

@Getter
@Embeddable
public class Password {

    private String password;

    public static Password create(String password) {
        return new Password(password);
    }

    protected Password() {}

    private Password(String password) {
        this.password = password;
    }
}
