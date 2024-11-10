package com.keybit.member.domain.model.vo;

import lombok.Getter;

public enum UserRole {
    USER("ROLE_USER"), ADMIN("ROLE_ADMIN"), SELLER("ROLE_SELLER");

    @Getter
    private final String name;

    UserRole(String name) {
        this.name = name;
    }
}
