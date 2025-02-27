package com.keybit.member.application.service;

import com.keybit.member.application.port.out.MemberOutputPort;
import com.keybit.member.application.port.in.InquiryMemberUsecase;
import com.keybit.member.domain.model.Member;
import com.keybit.member.framework.web.dto.MemberDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.NoSuchElementException;

@Service
@Transactional
@RequiredArgsConstructor
public class InquiryMember implements InquiryMemberUsecase {

    private final MemberOutputPort memberOutputPort;

    @Override
    public MemberDTO getMember(Long memberNo) {
        Member member = memberOutputPort.loadMember(memberNo)
                .orElseThrow(() -> new NoSuchElementException("없는 아이디입니다."));

        return MemberDTO.mapToDTO(member);
    }
}
