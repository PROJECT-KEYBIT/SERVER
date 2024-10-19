package com.keybit.member.application.outputport;

import com.keybit.member.domain.model.Member;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface MemberOutputPort {

    Optional<Member> loadMember(Long memberNo);
    Member save(Member member);
}
