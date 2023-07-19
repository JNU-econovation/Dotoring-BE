package com.theZ.dotoring.app.mento.controller;


import com.theZ.dotoring.app.mento.dto.*;
import com.theZ.dotoring.app.mento.handler.*;
import com.theZ.dotoring.app.mento.mapper.MentoMapper;
import com.theZ.dotoring.app.mento.service.MentoService;
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
public class MentoController {

    private final SaveMentoHandler saveMentoHandler;

    private final UpdateMentoInfoHandler updateMentoInfoHandler;

    private final UpdateMentoSysHandler updateMentoSysHandler;

    private final UpdateMentoAccountHandler updateMentoAccountHandler;

    private final FindAllMentoHandler findAllMentoHandler;

    private final MentoService mentoService;

    @PostMapping("/mento")
    public ApiResponse<ApiResponse.CustomBody<Void>> saveMento(@RequestPart List<MultipartFile> certifications ,@RequestPart @Valid MentoSignupRequestDTO mentoSignupRequestDTO) throws IOException {
        saveMentoHandler.execute(mentoSignupRequestDTO,certifications);
        return ApiResponseGenerator.success(HttpStatus.OK);
    }

    @GetMapping("/mento")
    public ApiResponse<ApiResponse.CustomBody<Slice<MentoCardResponseDTO>>> findAllMentoBySlice(
            @RequestParam(required = false) Long lastMentoId, @RequestParam(defaultValue = "10") Integer size, Long mentiId,
            @ModelAttribute MentoRequiredCondition mentoRequiredCondition){
        // todo springsecurity 적용한면, metiId를 받을 필요가 없다.
        mentoRequiredCondition.initCondition();
        return ApiResponseGenerator.success(findAllMentoHandler.execute(lastMentoId,size,mentiId, mentoRequiredCondition),HttpStatus.OK);
    }

    @GetMapping("/mento/{id}")
    public ApiResponse<ApiResponse.CustomBody<MentoCardResponseDTO>> findMentoById(@PathVariable Long id){
        MentoCardResponseDTO mentoCardResponseDTO = MentoMapper.from(mentoService.findMento(id));
        return ApiResponseGenerator.success(mentoCardResponseDTO,HttpStatus.OK);
    }

    // 소속, 직무 분야, n년차, 졸업 학과 수정
    @PutMapping("mento/info/{id}")
    public ApiResponse<ApiResponse.CustomBody<Void>> updateMentoInfoById(@RequestBody @Valid MentoInfoUpdateRequestDTO mentoInfoUpdateRequestDTO, @PathVariable Long id) throws IOException {
        updateMentoInfoHandler.execute(mentoInfoUpdateRequestDTO, id);
        return ApiResponseGenerator.success(HttpStatus.OK);
    }

    // 멘토링 방식 최초 작성
    @PutMapping("mento/system/{id}")
    public ApiResponse<ApiResponse.CustomBody<Void>> updateMentoSysById(@RequestBody @Valid MentoSysUpdateReqDTO mentoSysUpdateReqDTO, @PathVariable Long id) throws IOException {
        updateMentoSysHandler.execute(mentoSysUpdateReqDTO, id);
        return ApiResponseGenerator.success(HttpStatus.OK);
    }


    // 계정 설정(아이디, 비밀번호 재 설정)
    @PutMapping("mento/account/{id}")
    public ApiResponse<ApiResponse.CustomBody<Void>> updateMentoAccountById(@RequestBody @Valid MentoAccountUpdateReq mentoAccountUpdateReq, @PathVariable Long id) throws IOException {
        updateMentoAccountHandler.execute(mentoAccountUpdateReq, id);
        return ApiResponseGenerator.success(HttpStatus.OK);
    }
}