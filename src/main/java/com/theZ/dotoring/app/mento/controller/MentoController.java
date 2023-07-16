package com.theZ.dotoring.app.mento.controller;

import com.theZ.dotoring.app.mento.dto.MentoCardResponseDTO;
import com.theZ.dotoring.app.mento.dto.MentoSignupRequestDTO;
import com.theZ.dotoring.app.mento.dto.MentoRequiredCondition;
import com.theZ.dotoring.app.mento.handler.FindAllMentoHandler;
import com.theZ.dotoring.app.mento.handler.SaveMentoHandler;
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
public class MentoController {

    private final SaveMentoHandler saveMentoHandler;
    private final FindAllMentoHandler findAllMentoHandler;

    @PostMapping("/mento")
    public ApiResponse<ApiResponse.CustomBody<Void>> saveMento(@RequestBody @Valid MentoSignupRequestDTO mentoSignupRequestDTO){
        saveMentoHandler.execute(mentoSignupRequestDTO);
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

}
