package com.beben.tomasz.restaurant.standalone.exceptionmapper;

import lombok.AllArgsConstructor;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@Order(Ordered.HIGHEST_PRECEDENCE)
@ControllerAdvice
@AllArgsConstructor
public class ExceptionMapper {

    @ExceptionHandler(Exception.class)
    public ResponseEntity<?> handleException(Exception e) {
        ErrorResult errorResult = ErrorCodeMapper.mapException(e);
        return new ResponseEntity<>(errorResult, HttpStatus.UNPROCESSABLE_ENTITY);
    }
}
