package com.keybit.member.application.inputport;

import com.keybit.member.application.outputport.MemberOutputPort;
import com.keybit.member.application.usecase.InquiryMemberUsecase;
import com.keybit.member.domain.model.Member;
import com.keybit.member.framework.web.dto.MemberDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.NoSuchElementException;

@Service
@Transactional
@RequiredArgsConstructor
public class InquiryMemberInputPort implements InquiryMemberUsecase {

    private final MemberOutputPort memberOutputPort;

    @Override
    public MemberDTO getMember(Long memberNo) {
        Member member = memberOutputPort.loadMember(memberNo)
                .orElseThrow(() -> new NoSuchElementException("없는 아이디입니다."));

        return MemberDTO.mapToDTO(member);
    }
}
