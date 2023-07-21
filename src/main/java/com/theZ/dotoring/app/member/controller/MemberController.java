package com.theZ.dotoring.app.member.controller;

import com.theZ.dotoring.app.member.dto.*;
import com.theZ.dotoring.app.member.service.MemberDuplicateValidateService;
import com.theZ.dotoring.app.member.service.MemberEmailService;
import com.theZ.dotoring.common.ApiResponse;
import com.theZ.dotoring.common.ApiResponseGenerator;

import com.theZ.dotoring.enums.Job;
import com.theZ.dotoring.enums.Major;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.mail.MessagingException;
import javax.validation.Valid;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
public class MemberController {

    private final MemberDuplicateValidateService memberDuplicateValidateService;
    private final MemberEmailService memberEmailService;

    @PostMapping("/member/validate-nickname")
    public ApiResponse<ApiResponse.CustomBody<Void>> validateMemberNickname(@RequestBody @Valid MemberNicknameRequestDTO memberNicknameRequestDTO){
        memberDuplicateValidateService.validateNickname(memberNicknameRequestDTO);
        return ApiResponseGenerator.success(HttpStatus.OK);
    }

    @PostMapping("/member/validate-loginId")
    public ApiResponse<ApiResponse.CustomBody<Void>> validateMemberLoginId(@RequestBody @Valid MemberLoginIdRequestDTO memberLoginIdRequestDTO){
        memberDuplicateValidateService.validateLoginId(memberLoginIdRequestDTO.getLoginId());
        return ApiResponseGenerator.success(HttpStatus.OK);
    }

    @GetMapping("/member/email")
    public ApiResponse<ApiResponse.CustomBody<MemberEmailCodeResponseDTO>> findLoginId(@Valid MemberEmailRequestDTO memberEmailRequestDTO) throws MessagingException {
        MemberEmailCodeResponseDTO memberEmailCodeResponseDTO = memberEmailService.sendEmail(memberEmailRequestDTO);
        return ApiResponseGenerator.success(memberEmailCodeResponseDTO,HttpStatus.OK);
    }

    @GetMapping("/member/job-major")
    public ApiResponse<ApiResponse.CustomBody<MemberJobAndMajorResponseDTO>> findJobAndMajor(){
        List<String> jobs = Job.getJobs().stream().map(j -> j.toString()).toList();
        List<String> majors = Major.getMajors().stream().map(m -> m.toString()).toList();

        MemberJobAndMajorResponseDTO memberJobAndMajorResponseDTO = MemberJobAndMajorResponseDTO.builder()
                .jobs(jobs)
                .majors(majors)
                .build();

        return ApiResponseGenerator.success(memberJobAndMajorResponseDTO,HttpStatus.OK);
    }


}
