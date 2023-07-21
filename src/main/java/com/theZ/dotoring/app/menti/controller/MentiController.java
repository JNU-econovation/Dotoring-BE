package com.theZ.dotoring.app.menti.controller;


import com.theZ.dotoring.app.menti.dto.*;
import com.theZ.dotoring.app.menti.handler.*;
import com.theZ.dotoring.app.menti.mapper.MentiMapper;
import com.theZ.dotoring.app.menti.service.MentiService;
import com.theZ.dotoring.app.mento.dto.*;
import com.theZ.dotoring.common.ApiResponse;
import com.theZ.dotoring.common.ApiResponseGenerator;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Slice;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.Valid;
import java.io.IOException;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
public class MentiController {

    private final SaveMentiHandler mentiHandler;
    private final FindAllMentiHandler findAllMentiHandler;

    private final UpdateMentiInfoHandler updateMentiInfoHandler;

    private final UpdateMentiAccountHandler updateMentiAccountHandler;

    private final UpdateMentiSysHandler updateMentiSysHandler;

    private final MentiService mentiService;

    @PostMapping("/menti")
    public ApiResponse<ApiResponse.CustomBody<Void>> saveMenti(@RequestPart List<MultipartFile> certifications,@RequestPart @Valid MentiSignupRequestDTO mentiSignupRequestDTO) throws IOException {
        mentiHandler.execute(mentiSignupRequestDTO,certifications);
        return ApiResponseGenerator.success(HttpStatus.OK);
    }

    /**
     findAllMentiBySlice () -> 멘토 입장에서 멘티들을 학과, 희망 직무별로 필터링하는 메서드
        long lastMentiId : 페이징을 위한 마지막 멘티 아이디
        Integer size : 페이징을 위한 페이징 크기
        Long mentoId : 추후 Security 도입 후 Principle로 대체
        MentiRequiredCondition mentiRequiredCondition : 유저로부터 받은 학과, 직무들을 저장하는 DTO
     */

    @GetMapping("/menti")
    public ApiResponse<ApiResponse.CustomBody<Slice<MentiCardResponseDTO>>> findAllMentiBySlice(
            @RequestParam(required = false) Long lastMentiId, @RequestParam(defaultValue = "10") Integer size, Long mentoId,
            @ModelAttribute MentiRequiredCondition mentiRequiredCondition){
        // 컨트롤러에서 호출하는 이유? -> 컨트롤러가 아니라 Service에서 해당 메서드를 호출하는 것은 어떨까? 컨트롤러의 책임이 아니라 생각이들었음.
        mentiRequiredCondition.initCondition();
        return ApiResponseGenerator.success(findAllMentiHandler.execute(lastMentiId, size, mentoId, mentiRequiredCondition),HttpStatus.OK);
    }

    @GetMapping("/menti/{id}")
    public ApiResponse<ApiResponse.CustomBody<MentiCardResponseDTO>> findMentiById(@PathVariable Long id){
        MentiCardResponseDTO mentiCardResponseDTO = MentiMapper.from(mentiService.findMenti(id));
        return ApiResponseGenerator.success(mentiCardResponseDTO,HttpStatus.OK);
    }

    // 소속, 직무 분야, n년차, 졸업 학과 수정
    @PutMapping("menti/info/{id}")
    public ApiResponse<ApiResponse.CustomBody<Void>> updateMentiInfoById(@RequestBody @Valid MentiInfoUpdateRequestDTO mentiInfoUpdateRequestDTO, @PathVariable Long id) throws IOException {
        updateMentiInfoHandler.execute(mentiInfoUpdateRequestDTO, id);
        return ApiResponseGenerator.success(HttpStatus.OK);
    }

    // 멘토링 방식 최초 작성
    @PutMapping("menti/system/{id}")
    public ApiResponse<ApiResponse.CustomBody<Void>> updateMentiSysById(@RequestBody @Valid MentiSysUpdateReqDTO mentiSysUpdateReqDTO, @PathVariable Long id) throws IOException {
        updateMentiSysHandler.execute(mentiSysUpdateReqDTO, id);
        return ApiResponseGenerator.success(HttpStatus.OK);
    }


    // 계정 설정(아이디, 비밀번호 재 설정)
    @PutMapping("menti/account/{id}")
    public ApiResponse<ApiResponse.CustomBody<Void>> updateMentiAccountById(@RequestBody @Valid MentiAccountUpdateReq mentiAccountUpdateReq, @PathVariable Long id) throws IOException {
        updateMentiAccountHandler.execute(mentiAccountUpdateReq, id);
        return ApiResponseGenerator.success(HttpStatus.OK);
    }


}
