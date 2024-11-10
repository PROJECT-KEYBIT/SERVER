package com.keybit.member.framework.web.dto.security;

import com.keybit.member.domain.model.vo.UserRole;
import lombok.Getter;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;

import java.io.Serial;
import java.util.stream.Collectors;

@Getter
public class UserPrincipal extends User {

    @Serial
    private static final long serialVersionUID = 761989917486427370L;
    private final AuthenticatedAccount account;

    public UserPrincipal(AuthenticatedAccount account) {
        super(account.getMemId(), account.getPassword(), account
                .getRoles().stream()
                .map(UserRole::getName)
                .map(SimpleGrantedAuthority::new)
                .collect(Collectors.toUnmodifiableSet()));
        this.account = account;
    }
}
