package com.keybit.webgateway.authorization.jwt;

import com.keybit.webgateway.authorization.role.Role;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.Set;

@Getter
@AllArgsConstructor
public class TokenUser {
    private String id;
    private Set<Role> role;
}
