package com.keybit.member.framework.web.controller;

import com.keybit.member.application.usecase.AddMemberUsecase;
import com.keybit.member.application.usecase.InquiryMemberUsecase;
import com.keybit.member.framework.web.dto.MemberDTO;
import com.keybit.member.framework.web.dto.request.SignUpInfo;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Tag(name = "[회원-API]")
@Controller
@RequiredArgsConstructor
@RequestMapping("/api")
public class MemberController {

    private final AddMemberUsecase addMemberUsecase;
    private final InquiryMemberUsecase inquiryMemberUsecase;

    @PostMapping("/member/join")
    public ResponseEntity<MemberDTO> createMember(
            @RequestBody SignUpInfo signUpInfo
    ) {
        MemberDTO member = addMemberUsecase.createMember(signUpInfo);
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(member);
    }

    @GetMapping("/member/{memberNo}")
    public ResponseEntity<MemberDTO> getMember(@PathVariable Long memberNo) {
        return ResponseEntity.ok(inquiryMemberUsecase.getMember(memberNo));
    }
}
