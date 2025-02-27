package com.keybit.member.application.port.in;

import com.keybit.member.framework.web.dto.MemberDTO;

public interface InquiryMemberUsecase {

    MemberDTO getMember(Long memberNo);
}
