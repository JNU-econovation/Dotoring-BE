package com.theZ.dotoring.app.menti.controller;


import com.theZ.dotoring.app.menti.dto.MentiSignupDTO;
import com.theZ.dotoring.app.menti.handler.SaveMentiHandler;
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
@RequestMapping("/api")
public class MentiController {

    private final SaveMentiHandler mentiHandler;

    @PostMapping("/menti")
    public ApiResponse<ApiResponse.SuccessBody<Void>> saveMenti(@RequestBody @Valid MentiSignupDTO mentiSignupDTO){
        mentiHandler.execute(mentiSignupDTO);
        return ApiResponseGenerator.success(HttpStatus.OK);
    }
}
