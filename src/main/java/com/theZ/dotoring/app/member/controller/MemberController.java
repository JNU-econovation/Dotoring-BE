package com.theZ.dotoring.app.member.controller;

import com.theZ.dotoring.app.member.dto.MemberEmailCodeDTO;
import com.theZ.dotoring.app.member.dto.MemberEmailDTO;
import com.theZ.dotoring.app.member.dto.MemberLoginIdDTO;
import com.theZ.dotoring.app.member.dto.MemberNicknameDTO;
import com.theZ.dotoring.app.member.service.MemberDuplicateValidateService;
import com.theZ.dotoring.app.member.service.MemberEmailValidateService;
import com.theZ.dotoring.common.ApiResponse;
import com.theZ.dotoring.common.ApiResponseGenerator;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/")
public class MemberController {
    private final MemberDuplicateValidateService memberDuplicateValidateService;
    private final MemberEmailValidateService memberEmailValidateService;

    @PostMapping("/member/nickname")
    public ApiResponse<ApiResponse.CustomBody<Void>> validateMemberNickname(@RequestBody @Valid MemberNicknameDTO memberNicknameDTO){
        memberDuplicateValidateService.validateNickname(memberNicknameDTO);
        return ApiResponseGenerator.success(HttpStatus.OK);
    }

    @PostMapping("/member/loginId")
    public ApiResponse<ApiResponse.CustomBody<Void>> validateMemberLoginId(@RequestBody @Valid MemberLoginIdDTO memberLoginIdDTO){
        memberDuplicateValidateService.validateLoginId(memberLoginIdDTO);
        return ApiResponseGenerator.success(HttpStatus.OK);
    }

    @PostMapping("member/email")
    public ApiResponse<ApiResponse.CustomBody<MemberEmailCodeDTO>> validateMemberEmail(@RequestBody @Valid MemberEmailDTO memberEmailDTO){
        MemberEmailCodeDTO memberEmailCodeDTO = memberEmailValidateService.validateEmail(memberEmailDTO);
        return ApiResponseGenerator.success(memberEmailCodeDTO,HttpStatus.OK);
    }

}
