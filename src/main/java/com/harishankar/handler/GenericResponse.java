package com.harishankar.handler;

import java.time.LocalDateTime;
import java.util.LinkedHashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.ObjectUtils;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class GenericResponse<T> {

    private LocalDateTime timestamp;
    private int statusCode;       // e.g., 200, 404
    private String status;        // SUCCESS, ERROR
    private String message;       // e.g., "Data saved successfully"
    private T data;               // Typed data object

    /**
     * Converts this DTO to a ResponseEntity
     */
    public ResponseEntity<Map<String, Object>> toResponseEntity(HttpStatus httpStatus) {
        Map<String, Object> body = new LinkedHashMap<>();
        body.put("timestamp", timestamp != null ? timestamp : LocalDateTime.now());
        body.put("statusCode", httpStatus.value());
        body.put("status", status);
        body.put("message", message);
        if (!ObjectUtils.isEmpty(data)) {
            body.put("data", data);
        }
        return new ResponseEntity<>(body, httpStatus);
    }

    /**
     * Convenience method for successful responses
     */
    public static <T> ResponseEntity<Map<String, Object>> success(T data, String message) {
        GenericResponse<T> response = GenericResponse.<T>builder()
                .timestamp(LocalDateTime.now())
                .status("SUCCESS")
                .statusCode(HttpStatus.OK.value())
                .message(message)
                .data(data)
                .build();
        return response.toResponseEntity(HttpStatus.OK);
    }

    /**
     * Convenience method for error responses
     */
    public static ResponseEntity<Map<String, Object>> error(HttpStatus status, String message) {
        GenericResponse<Object> response = GenericResponse.builder()
                .timestamp(LocalDateTime.now())
                .status("ERROR")
                .statusCode(status.value())
                .message(message)
                .build();
        return response.toResponseEntity(status);
    }
}
