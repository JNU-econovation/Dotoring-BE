package com.theZ.dotoring.app.mento.controller;

import com.theZ.dotoring.app.mento.dto.MentoSignupDTO;
import com.theZ.dotoring.app.mento.handler.SaveMentoHandler;
import com.theZ.dotoring.common.ApiResponse;
import com.theZ.dotoring.common.ApiResponseGenerator;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
public class MentoController {

    private final SaveMentoHandler saveMentoHandler;

    @PostMapping("/mento")
    public ApiResponse<ApiResponse.CustomBody<Void>> saveMento(@RequestBody @Valid MentoSignupDTO mentoSignupDTO){
        saveMentoHandler.execute(mentoSignupDTO);
        return ApiResponseGenerator.success(HttpStatus.OK);
    }

}
