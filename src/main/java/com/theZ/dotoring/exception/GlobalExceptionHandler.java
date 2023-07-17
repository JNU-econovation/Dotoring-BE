package com.theZ.dotoring.exception;

import com.theZ.dotoring.common.ApiResponse;
import com.theZ.dotoring.common.ApiResponseGenerator;
import com.theZ.dotoring.common.MessageCode;
import org.apache.tomcat.util.http.fileupload.impl.FileSizeLimitExceededException;
import org.apache.tomcat.util.http.fileupload.impl.SizeLimitExceededException;
import org.springframework.http.HttpStatus;
import org.springframework.web.HttpMediaTypeNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.io.FileNotFoundException;
import java.io.IOException;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(IOException.class)
    public ApiResponse<ApiResponse.CustomBody> handleIOException(IOException ioException){
        return ApiResponseGenerator.fail(MessageCode.FILE_NOT_INPUT_OUTPUT.getCode(),MessageCode.FILE_NOT_INPUT_OUTPUT.getValue(), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(IllegalStateException.class)
    public ApiResponse<ApiResponse.CustomBody> handleIllegalStateException(IllegalStateException illegalStateException){
        return ApiResponseGenerator.fail(MessageCode.VALIDATION_FAIL.getCode(),illegalStateException.getMessage(), HttpStatus.BAD_REQUEST);
    }


    @ExceptionHandler(IllegalArgumentException.class)
    public ApiResponse<ApiResponse.CustomBody> handleIllegalArgumentException(IllegalArgumentException illegalArgumentException){
        return ApiResponseGenerator.fail(MessageCode.VALIDATION_FAIL.getCode(),illegalArgumentException.getMessage(), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(HttpMediaTypeNotSupportedException.class)
    public ApiResponse<ApiResponse.CustomBody> handleHttpMediaTypeNotSupportedException(HttpMediaTypeNotSupportedException e){
        return ApiResponseGenerator.fail(MessageCode.NOT_ALLOWED_FILE_EXT.getCode(),MessageCode.NOT_ALLOWED_FILE_EXT.getValue(), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(SizeLimitExceededException.class)
    public ApiResponse<ApiResponse.CustomBody> handleSizeLimitExceededException(SizeLimitExceededException e){
        return ApiResponseGenerator.fail(MessageCode.LIMIT_FILE_SIZE.getCode(),MessageCode.LIMIT_FILE_SIZE.getValue(),HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(FileSizeLimitExceededException.class)
    public ApiResponse<ApiResponse.CustomBody> handleIOFileSizeLimitExceededException(FileSizeLimitExceededException e){
        return ApiResponseGenerator.fail(MessageCode.LIMIT_FILE_SIZE.getCode(),MessageCode.LIMIT_FILE_SIZE.getValue(),HttpStatus.BAD_REQUEST);
    }
    @ExceptionHandler(ExtentionNotAllowedException.class)
    public ApiResponse<ApiResponse.CustomBody> handleExtentionNotAllowedException(ExtentionNotAllowedException e){
        return ApiResponseGenerator.fail(e.messageCode.getCode(),e.messageCode.getValue(), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(FileNotFoundException.class)
    public ApiResponse<ApiResponse.CustomBody> handleFileNotFoundException(FileNotFoundException fileNotFoundException){
        return ApiResponseGenerator.fail(MessageCode.FIlE_NOT_FOUND.getCode(),MessageCode.FIlE_NOT_FOUND.getValue(), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(NicknameDuplicateException.class)
    public ApiResponse<ApiResponse.CustomBody> handleNicknameDuplicateException(NicknameDuplicateException e){
        return ApiResponseGenerator.fail(e.messageCode.getCode(),e.messageCode.getValue(), HttpStatus.CONFLICT);
    }

    @ExceptionHandler(LoginIdDuplicateException.class)
    public ApiResponse<ApiResponse.CustomBody> handleLoginIdDuplicateException(LoginIdDuplicateException e){
        return ApiResponseGenerator.fail(e.messageCode.getCode(),e.messageCode.getValue(), HttpStatus.CONFLICT);
    }


    @ExceptionHandler(RuntimeException.class)
    public ApiResponse<ApiResponse.CustomBody> handleRuntimeException(RuntimeException e){
        return ApiResponseGenerator.fail(e.getMessage(),null, HttpStatus.BAD_REQUEST);
    }
    
    // 유효성 어노테이션 핸들링
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ApiResponse<ApiResponse.CustomBody> handleMethodArgumentNotValidException(MethodArgumentNotValidException e){
        return ApiResponseGenerator.fail(e.getFieldError().getDefaultMessage(),null, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(NotFoundLetterException.class)
    public ApiResponse<ApiResponse.CustomBody> handleNotFindLetterException(NotFoundLetterException e){
        return ApiResponseGenerator.fail(MessageCode.LETTER_NOT_FOUND.getCode(),MessageCode.LETTER_NOT_FOUND.getValue(), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(NotFoundRoomException.class)
    public ApiResponse<ApiResponse.CustomBody> handleNotFindRoomException(NotFoundRoomException e){
        return ApiResponseGenerator.fail(MessageCode.ROOM_NOT_FOUND.getCode(),MessageCode.ROOM_NOT_FOUND.getValue(), HttpStatus.BAD_REQUEST);
    }

}
