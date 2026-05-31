package com.pigeonkim.jobmatcheragent;

import org.jsoup.HttpStatusException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

// 앱 전체의 예외를 한 곳에서 처리
// C#의 ExceptionFilter 또는 미들웨어와 동일한 역할
@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(HttpStatusException.class)
    public ResponseEntity<String> handleHttpStatus(HttpStatusException e) {
        return ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .body("페이지 오류: " + e.getStatusCode() + " - " + e.getUrl());
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<String> handleException(Exception e) {
        return ResponseEntity
                .status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body("서버 오류: " + e.getMessage());
    }
}