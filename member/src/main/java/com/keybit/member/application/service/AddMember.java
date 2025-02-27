package com.keybit.member.application.service;

import com.keybit.member.application.port.out.MemberOutputPort;
import com.keybit.member.application.port.in.AddMemberUsecase;
import com.keybit.member.domain.model.Member;
import com.keybit.member.framework.web.dto.MemberDTO;
import com.keybit.member.framework.web.dto.request.SignUpInfo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
public class AddMember implements AddMemberUsecase {

    private final MemberOutputPort outputPort;
    @Override
    public MemberDTO createMember(SignUpInfo signUpInfo) {
        Member member = Member.registerMember(
                signUpInfo.id(),
                signUpInfo.name(),
                signUpInfo.password(),
                signUpInfo.email());

        Member savedMember = outputPort.save(member);
        return MemberDTO.mapToDTO(savedMember);
    }
}
