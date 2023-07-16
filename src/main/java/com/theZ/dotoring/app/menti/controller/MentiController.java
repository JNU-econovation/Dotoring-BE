package com.theZ.dotoring.app.menti.controller;


import com.theZ.dotoring.app.menti.dto.MentiCardResponseDTO;
import com.theZ.dotoring.app.menti.dto.MentiSignupRequestDTO;
import com.theZ.dotoring.app.menti.handler.FindAllMentiHandler;
import com.theZ.dotoring.app.menti.handler.SaveMentiHandler;
import com.theZ.dotoring.app.menti.mapper.MentiMapper;
import com.theZ.dotoring.app.menti.service.MentiService;
import com.theZ.dotoring.app.mento.dto.MentoCardResponseDTO;
import com.theZ.dotoring.common.ApiResponse;
import com.theZ.dotoring.common.ApiResponseGenerator;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Slice;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
public class MentiController {

    private final SaveMentiHandler mentiHandler;
    private final FindAllMentiHandler findAllMentiHandler;
    private final MentiService mentiService;
    @PostMapping("/menti")
    public ApiResponse<ApiResponse.CustomBody<Void>> saveMenti(@RequestBody @Valid MentiSignupRequestDTO mentiSignupRequestDTO){
        mentiHandler.execute(mentiSignupRequestDTO);
        return ApiResponseGenerator.success(HttpStatus.OK);
    }

    @GetMapping("/menti")
    public ApiResponse<ApiResponse.CustomBody<Slice<MentiCardResponseDTO>>> findAllMentiBySlice(
            @RequestParam(required = false) Long lastMentiId, @RequestParam(defaultValue = "10") Integer size, Long mentoId){
        // todo springsecurity 적용한면, metoId를 받을 필요가 없다.
        return ApiResponseGenerator.success(findAllMentiHandler.execute(lastMentiId,size,mentoId),HttpStatus.OK);
    }

    @GetMapping("/menti/{id}")
    public ApiResponse<ApiResponse.CustomBody<MentiCardResponseDTO>> findMentiById(@PathVariable Long id){
        MentiCardResponseDTO mentiCardResponseDTO = MentiMapper.from(mentiService.findMenti(id));
        return ApiResponseGenerator.success(mentiCardResponseDTO,HttpStatus.OK);
    }

}
