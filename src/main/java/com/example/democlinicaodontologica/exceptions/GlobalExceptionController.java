package com.example.democlinicaodontologica.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionController {

    @ExceptionHandler({ResourceNotfoundException.class})
    public ResponseEntity<String> processResourceNotFoundException(ResourceNotfoundException resourceNotfoundException){

        return ResponseEntity.status(HttpStatus.SERVICE_UNAVAILABLE).body(resourceNotfoundException.getMessage());
    }

}
