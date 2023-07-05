package com.theZ.dotoring.app.member.controller;

import com.theZ.dotoring.app.member.dto.*;
import com.theZ.dotoring.app.member.service.MemberDuplicateValidateService;
import com.theZ.dotoring.app.member.service.MemberEmailValidateService;
import com.theZ.dotoring.app.member.service.MemberService;
import com.theZ.dotoring.common.ApiResponse;
import com.theZ.dotoring.common.ApiResponseGenerator;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
public class MemberController {

    private final MemberDuplicateValidateService memberDuplicateValidateService;
    private final MemberEmailValidateService memberEmailValidateService;

    @PostMapping("/member/nickname")
    public ApiResponse<ApiResponse.CustomBody<Void>> validateMemberNickname(@RequestBody @Valid MemberNicknameRequestDTO memberNicknameRequestDTO){
        memberDuplicateValidateService.validateNickname(memberNicknameRequestDTO);
        return ApiResponseGenerator.success(HttpStatus.OK);
    }

    @PostMapping("/member/loginId")
    public ApiResponse<ApiResponse.CustomBody<Void>> validateMemberLoginId(@RequestBody @Valid MemberLoginIdRequestDTO memberLoginIdRequestDTO){
        memberDuplicateValidateService.validateLoginId(memberLoginIdRequestDTO);
        return ApiResponseGenerator.success(HttpStatus.OK);
    }

    @PostMapping("/member/email")
    public ApiResponse<ApiResponse.CustomBody<MemberEmailCodeResponseDTO>> validateMemberEmail(@RequestBody @Valid MemberEmailRequestDTO memberEmailRequestDTO){
        MemberEmailCodeResponseDTO memberEmailCodeResponseDTO = memberEmailValidateService.validateEmail(memberEmailRequestDTO);
        return ApiResponseGenerator.success(memberEmailCodeResponseDTO,HttpStatus.OK);
    }


}
