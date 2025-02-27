package com.keybit.member.framework.jpaadapter;

import com.keybit.member.application.port.out.MemberOutputPort;
import com.keybit.member.domain.model.Member;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class MemberJpaAdapter implements MemberOutputPort {

    private final MemberRepository memberRepository;

    @Override
    public Optional<Member> loadMember(Long memberNo) {
        return memberRepository.findById(memberNo);
    }

    @Override
    public Member save(Member member) {
        return memberRepository.save(member);
    }

    @Override
    public Optional<Member> loadMemberById(String memberId) {
        return memberRepository.findByIdName_Id(memberId);
    }
}
