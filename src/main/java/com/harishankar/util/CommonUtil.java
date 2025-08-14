package com.harishankar.util;

import java.time.LocalDateTime;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.harishankar.handler.GenericResponse;

import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class CommonUtil {

    public enum ResponseStatus {
        SUCCESS, FAILED
    }

    /**
     * General method to build API responses
     */
    private static <T> ResponseEntity<?> buildResponse(
            T data, String message, ResponseStatus status, HttpStatus httpStatus) {

        GenericResponse<T> response = GenericResponse.<T>builder()
                .timestamp(LocalDateTime.now())
                .statusCode(httpStatus.value())
                .status(status.name())
                .message(message)
                .data(data)
                .build();

        if (status == ResponseStatus.FAILED) {
            log.error("API Error: {} - {}", message, data);
        }

        return response.toResponseEntity(httpStatus);
    }

    // -------------------------
    // Public Convenience Methods
    // -------------------------

    public static <T> ResponseEntity<?> success(T data, HttpStatus status) {
        return buildResponse(data, "", ResponseStatus.SUCCESS, status);
    }

    public static ResponseEntity<?> successMessage(String message, HttpStatus status) {
        return buildResponse(null, message, ResponseStatus.SUCCESS, status);
    }

    public static <T> ResponseEntity<?> error(T data, HttpStatus status) {
        return buildResponse(data, "Something went wrong!", ResponseStatus.FAILED, status);
    }

    public static ResponseEntity<?> errorMessage(String message, HttpStatus status) {
        return buildResponse(null, message, ResponseStatus.FAILED, status);
    }

    // -------------------------
    // Utility
    // -------------------------

    public static String getUrl(HttpServletRequest request) {
        String apiUrl = request.getRequestURI();
        return apiUrl.replace(request.getServletPath(), "");
    }
}
