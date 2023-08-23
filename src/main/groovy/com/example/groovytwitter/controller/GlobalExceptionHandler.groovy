package com.example.groovytwitter.controller

import com.example.groovytwitter.exception.BaseResponse
import com.example.groovytwitter.exception.UserValidationException
import org.springframework.http.HttpHeaders
import org.springframework.http.HttpStatusCode
import org.springframework.http.ResponseEntity
import org.springframework.validation.ObjectError
import org.springframework.web.bind.MethodArgumentNotValidException
import org.springframework.web.bind.annotation.ControllerAdvice
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.context.request.WebRequest
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler

import java.time.LocalDateTime

@ControllerAdvice
class GlobalExceptionHandler extends ResponseEntityExceptionHandler {
    @ExceptionHandler(UserValidationException.class)
    ResponseEntity<BaseResponse<String>> handleException(UserValidationException exception) {
        BaseResponse<String> body = new BaseResponse()
        body.setTimestamp(LocalDateTime.now())
        body.setValue(exception.getMessage())
        return ResponseEntity.badRequest().body(body)
    }

    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(
            MethodArgumentNotValidException ex,
            HttpHeaders headers,
            HttpStatusCode status,
            WebRequest request
    ) {

        BaseResponse<List<String>> body = new BaseResponse()
        body.setTimestamp(LocalDateTime.now())
        List<String> errors = ex.getBindingResult().getAllErrors()
                .stream()
                .map(ObjectError::getDefaultMessage)
                .toList()
        body.setValue(errors)
        return new ResponseEntity<>(body, status)
    }
}

