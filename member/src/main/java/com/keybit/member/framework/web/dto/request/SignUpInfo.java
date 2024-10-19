package com.keybit.member.framework.web.dto.request;

public record SignUpInfo(
        String id,
        String name,
        String password,
        String email
) {
}
