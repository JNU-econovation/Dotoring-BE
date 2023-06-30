package com.theZ.dotoring.common;

import lombok.experimental.UtilityClass;
import org.springframework.http.HttpStatus;

@UtilityClass
public class ApiResponseGenerator {

    public static ApiResponse<ApiResponse.CustomBody<Void>> success(final HttpStatus status) {
        return new ApiResponse<>(new ApiResponse.CustomBody<Void>(true,null,null),status);
    }

    public static <D> ApiResponse<ApiResponse.CustomBody<D>> success(final D response, final HttpStatus status) {
        return new ApiResponse<>(new ApiResponse.CustomBody(true,response,null), status);
    }

    public static ApiResponse<ApiResponse.CustomBody> fail(String message, String code, final HttpStatus status) {
        return new ApiResponse<>(new ApiResponse.CustomBody(false,null,new Error(message,code,status.toString())),status);
    }

}
