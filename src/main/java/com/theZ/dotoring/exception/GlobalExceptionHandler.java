package com.theZ.dotoring.exception;

import com.theZ.dotoring.common.ApiResponse;
import com.theZ.dotoring.common.ApiResponseGenerator;
import com.theZ.dotoring.common.MessageCode;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.io.IOException;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(IOException.class)
    public ApiResponse<ApiResponse.FailureBody> handleIOException(IOException ioException){
        return ApiResponseGenerator.fail(MessageCode.FILE_NOT_INPUT_OUTPUT.getCode(),MessageCode.FILE_NOT_INPUT_OUTPUT.getValue(), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(IllegalStateException.class)
    public ApiResponse<ApiResponse.FailureBody> handleIOException(IllegalStateException illegalStateException){
        return ApiResponseGenerator.fail(MessageCode.FILE_NOT_STATE.getCode(),MessageCode.FILE_NOT_STATE.getValue(), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(ExtentionNotAllowedException.class)
    public ApiResponse<ApiResponse.FailureBody> handleIOException(ExtentionNotAllowedException extentionNotAllowedException){
        return ApiResponseGenerator.fail(MessageCode.NOT_ALLOWED_FILE_EXT.getCode(),MessageCode.NOT_ALLOWED_FILE_EXT.getValue(), HttpStatus.BAD_REQUEST);
    }


}
