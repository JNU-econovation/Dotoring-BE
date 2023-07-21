package com.theZ.dotoring.app.member.controller;

import com.theZ.dotoring.app.member.dto.MemberInfoResponseDTO;
import com.theZ.dotoring.app.member.service.MemberManageService;
import com.theZ.dotoring.common.ApiResponse;
import com.theZ.dotoring.common.ApiResponseGenerator;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
public class MemberManagerController {

    private final MemberManageService memberManageService;

    @GetMapping("/admin/wait-member")
    public ApiResponse<ApiResponse.CustomBody<Page<MemberInfoResponseDTO>>> getWaitMember(Pageable pageable, @RequestParam(required = false) String condition){

        if(!check(condition)){
            throw new IllegalArgumentException("condition은 I와 O만 들어올 수 있습니다.");
        }
        Page<MemberInfoResponseDTO> memberInfoResponseDTO = memberManageService.findWaitMember(pageable, condition);
        return ApiResponseGenerator.success(memberInfoResponseDTO, HttpStatus.OK);
    }

    private boolean check(String condition) {
        if(condition != null){
            return condition.equals("O") || condition.equals("I");
        }
        return true;
    }

    @PatchMapping("/admin/member")
    public ApiResponse<ApiResponse.CustomBody<Void>> approveMember(@RequestParam Long id){
        memberManageService.approveMember(id);
        return ApiResponseGenerator.success(HttpStatus.OK);
    }


}
