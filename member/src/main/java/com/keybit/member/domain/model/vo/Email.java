package com.keybit.member.domain.model.vo;

import jakarta.persistence.Embeddable;
import lombok.Getter;

@Embeddable
public class Email {

    @Getter
    private String address;

    public static Email create(String address) {
        return new Email(address);
    }

    protected Email() {}
    private Email(String address) {
        this.address = address;
    }
}
