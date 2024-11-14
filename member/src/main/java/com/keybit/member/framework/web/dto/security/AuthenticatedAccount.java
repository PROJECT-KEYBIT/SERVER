package com.keybit.member.framework.web.dto.security;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.keybit.member.domain.model.vo.UserRole;
import lombok.Builder;
import lombok.Getter;

import java.util.HashSet;
import java.util.Set;

@Getter
public class AuthenticatedAccount {

    private String memId;

    @JsonIgnore
    private String password;

    private Set<UserRole> roles = new HashSet<>();

    @Builder
    public AuthenticatedAccount(String memId, String password, Set<UserRole> roles) {
        this.memId = memId;
        this.password = password;
        this.roles = roles;
    }
}
