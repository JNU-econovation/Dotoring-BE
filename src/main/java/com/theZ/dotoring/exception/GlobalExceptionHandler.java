package com.theZ.dotoring.exception;

import com.theZ.dotoring.common.ApiResponse;
import com.theZ.dotoring.common.ApiResponseGenerator;
import com.theZ.dotoring.common.MessageCode;
import jakarta.validation.ConstraintViolationException;
import org.apache.tomcat.util.http.fileupload.impl.FileSizeLimitExceededException;
import org.apache.tomcat.util.http.fileupload.impl.SizeLimitExceededException;
import org.springframework.http.HttpStatus;
import org.springframework.web.HttpMediaTypeNotSupportedException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.server.UnsupportedMediaTypeStatusException;

import java.io.FileNotFoundException;
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

    @ExceptionHandler(HttpMediaTypeNotSupportedException.class)
    public ApiResponse<ApiResponse.FailureBody> handleIOException(HttpMediaTypeNotSupportedException e){
        return ApiResponseGenerator.fail(MessageCode.NOT_ALLOWED_FILE_EXT.getCode(),MessageCode.NOT_ALLOWED_FILE_EXT.getValue(), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(SizeLimitExceededException.class)
    public ApiResponse<ApiResponse.FailureBody> handleIOException(SizeLimitExceededException e){
        return ApiResponseGenerator.fail(MessageCode.LIMIT_FILE_SIZE.getCode(),MessageCode.LIMIT_FILE_SIZE.getValue(),HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(FileSizeLimitExceededException.class)
    public ApiResponse<ApiResponse.FailureBody> handleIOException(FileSizeLimitExceededException e){
        return ApiResponseGenerator.fail(MessageCode.LIMIT_FILE_SIZE.getCode(),MessageCode.LIMIT_FILE_SIZE.getValue(),HttpStatus.BAD_REQUEST);
    }
    @ExceptionHandler(ExtentionNotAllowedException.class)
    public ApiResponse<ApiResponse.FailureBody> handleIOException(ExtentionNotAllowedException e){
        return ApiResponseGenerator.fail(e.messageCode.getCode(),e.messageCode.getValue(), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(FileNotFoundException.class)
    public ApiResponse<ApiResponse.FailureBody> handleIOException(FileNotFoundException fileNotFoundException){
        return ApiResponseGenerator.fail(MessageCode.FIlE_NOT_FOUND.getCode(),MessageCode.FIlE_NOT_FOUND.getValue(), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(NicknameDuplicateException.class)
    public ApiResponse<ApiResponse.FailureBody> handleEmailDuplicateException(NicknameDuplicateException e){
        return ApiResponseGenerator.fail(e.messageCode.getCode(),e.messageCode.getValue(), HttpStatus.CONFLICT);
    }

    @ExceptionHandler(LoginIdDuplicateException.class)
    public ApiResponse<ApiResponse.FailureBody> handleEmailDuplicateException(LoginIdDuplicateException e){
        return ApiResponseGenerator.fail(e.messageCode.getCode(),e.messageCode.getValue(), HttpStatus.CONFLICT);
    }

    @ExceptionHandler(ConstraintViolationException.class)
    public ApiResponse<ApiResponse.FailureBody> handleEmailDuplicateException(ConstraintViolationException e){
        return ApiResponseGenerator.fail(MessageCode.VALIDATION_FAIL.getCode(),e.getMessage(), HttpStatus.CONFLICT);
    }






}
