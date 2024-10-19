package com.keybit.member.framework.jpaadaptor;

import com.keybit.member.application.outputport.MemberOutputPort;
import com.keybit.member.domain.model.Member;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class MemberJpaAdaptor implements MemberOutputPort {

    private final MemberRepository memberRepository;

    @Override
    public Optional<Member> loadMember(Long memberNo) {
        return memberRepository.findById(memberNo);
    }

    @Override
    public Member save(Member member) {
        return memberRepository.save(member);
    }
}
