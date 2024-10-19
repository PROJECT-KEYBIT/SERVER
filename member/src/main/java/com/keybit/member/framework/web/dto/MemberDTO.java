package com.keybit.member.framework.web.dto;

import com.keybit.member.domain.model.Member;

public record MemberDTO (

    Long memberNo,
    String id,
    String name,
    String email
) {
    public static MemberDTO mapToDTO(Member member) {
        return new MemberDTO(member.getMemberNo(), member.getId(), member.getName(), member.getEmail());
    }
}
