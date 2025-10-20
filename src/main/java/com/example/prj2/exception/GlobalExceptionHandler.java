package com.example.prj2.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler {

    // 1️⃣ Xử lý các lỗi kiểu RuntimeException
    @ExceptionHandler(RuntimeException.class)
    public ResponseEntity<String> handleRuntimeException(RuntimeException ex) {
        return ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .body("Lỗi hệ thống: " + ex.getMessage());
    }

    // 2️⃣ Xử lý các lỗi tổng quát (phòng khi lỗi không thuộc Runtime)
    @ExceptionHandler(Exception.class)
    public ResponseEntity<String> handleException(Exception ex) {
        return ResponseEntity
                .status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body("Đã xảy ra lỗi không xác định: " + ex.getMessage());
    }
}
