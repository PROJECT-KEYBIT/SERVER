package com.keybit.member.application.port.in;

import com.keybit.member.framework.web.dto.MemberDTO;
import com.keybit.member.framework.web.dto.request.SignUpInfo;

public interface AddMemberUsecase {

    MemberDTO createMember(SignUpInfo signUpInfo);
}
