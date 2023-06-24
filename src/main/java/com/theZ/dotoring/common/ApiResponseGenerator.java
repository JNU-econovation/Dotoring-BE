package com.theZ.dotoring.common;

import lombok.experimental.UtilityClass;
import org.springframework.http.HttpStatus;

@UtilityClass
public class ApiResponseGenerator {
    public static ApiResponse<ApiResponse.SuccessBody<Void>> success(final HttpStatus status) {
        return new ApiResponse<>(
                new ApiResponse.SuccessBody<>(
                        null, MessageCode.SUCCESS.getValue(), MessageCode.SUCCESS.getCode()),
                status);
    }

    public static ApiResponse<ApiResponse.SuccessBody<Void>> success(
            final HttpStatus status, MessageCode code) {
        return new ApiResponse<>(
                new ApiResponse.SuccessBody<>(null, code.getValue(), code.getCode()), status);
    }

    public static <D> ApiResponse<ApiResponse.SuccessBody<D>> success(
            final D data, final HttpStatus status) {
        return new ApiResponse<>(
                new ApiResponse.SuccessBody<>(
                        data, MessageCode.SUCCESS.getValue(), MessageCode.SUCCESS.getCode()),
                status);
    }

    public static <D> ApiResponse<ApiResponse.SuccessBody<D>> success(
            final D data, final HttpStatus status, MessageCode code) {
        return new ApiResponse<>(
                new ApiResponse.SuccessBody<>(data, code.getValue(), code.getCode()), status);
    }

    public static ApiResponse<Void> fail(final HttpStatus status) {
        return new ApiResponse<>(status);
    }

    public static ApiResponse<ApiResponse.FailureBody> fail(
            final ApiResponse.FailureBody body, final HttpStatus status) {
        return new ApiResponse<>(body, status);
    }

    public static ApiResponse<ApiResponse.FailureBody> fail(
            final String code, final String message, final HttpStatus status) {
        return new ApiResponse<>(new ApiResponse.FailureBody(code, message), status);
    }

    public static ApiResponse<ApiResponse.FailureBody> fail(
            final String message, final HttpStatus status) {
        return new ApiResponse<>(new ApiResponse.FailureBody(message), status);
    }
}
