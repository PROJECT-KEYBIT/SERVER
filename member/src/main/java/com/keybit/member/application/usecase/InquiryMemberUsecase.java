package com.keybit.member.application.usecase;

import com.keybit.member.framework.web.dto.MemberDTO;

public interface InquiryMemberUsecase {

    MemberDTO getMember(Long memberNo);
}
