package kr.co.estate.controller;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.ToString;

@Getter
@ToString
@RequiredArgsConstructor
public class ApiResponse<T> {
    private final int code;
    private final String message;
    private final T data;

    public static <T> ApiResponse<T> valueOf(T data) {
        return new ApiResponse<>(0, "정상적으로 처리되었습니다.", data);
    }

    public static <T> ApiResponse<T> of(int code, String message, T data) {
        return new ApiResponse<>(code, message, data);
    }
}
